// CLIENT ROBI
 
package client;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class ClientRobiSwing {

    private JFrame frame = null;

    private String title = "Client ROBI";

    private Font baskervilleFont = new Font("Baskerville", Font.PLAIN, 14);

    private Button button_start = null;
    private Button button_stop = null;

    private JTextPane txt_in = null; // zone de saisie expressions ROBI
    private JScrollPane s_txt_in = null;

    private JTextPane txt_out = null; // affichage des resultats
    private JScrollPane s_txt_out = null;

    public Socket socket;
    public PrintStream socket_printStream;
    public BufferedReader socket_bufferedReader;

    public String server = "localhost";
    public int port = 8000;

    public ClientRobiSwing() throws UnknownHostException, IOException {


        frame = new JFrame(title);
        Component contents = createComponents();
        frame.getContentPane().add(contents);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);

    }

    public Component createComponents() {

        JPanel panel = new JPanel();
        JPanel panel_button = new JPanel();
        panel_button.setLayout(new GridLayout(2, 2));
        button_start = new Button("Start");
        button_start.setFont(baskervilleFont);
        button_stop = new Button("Stop");
        button_stop.setFont(baskervilleFont);

        /*
         * Quand on appuie sur le bouton start, une socket est ouverte
         * Pour envoyer au serveur le contenu de la zone de saisie
         * Le champ de saisie est ensuite vidé
         * Puis la socket est fermee
         */

        button_start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    socket = new Socket(server, port);
                    socket_printStream = new PrintStream(socket.getOutputStream());
                    socket_bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                txt_out.setText(txt_out.getText() + "Envoi du programme\n");
                String txt_in_value = txt_in.getText() + '\0';
                System.out.print(txt_in_value);
                socket_printStream.println(txt_in_value);
                txt_in.setText("");
                String response = "";

                try {
                    response = socket_bufferedReader.readLine();
                } catch (IOException e1) {
                	e1.printStackTrace();
                }
                while (response != null) {
                    txt_out.setText(txt_out.getText() + response + "\n");
                    try {
                        response = socket_bufferedReader.readLine();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Closing window after clicking on Stop

        button_stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });

        panel_button.add(button_start);
        panel_button.add(button_stop);
        panel.add(panel_button, BorderLayout.SOUTH);

        txt_in = new JTextPane();
        txt_in.setFont(baskervilleFont);
        txt_in.setPreferredSize(new Dimension(600, 200));
        s_txt_in = new JScrollPane(txt_in);
        panel.add(s_txt_in, BorderLayout.CENTER);

        txt_out = new JTextPane();
        txt_out.setEditable(false);
        txt_out.setFont(baskervilleFont);
        txt_out.setPreferredSize(new Dimension(600, 200));
        s_txt_out = new JScrollPane(txt_out);
        panel.add(s_txt_out, BorderLayout.NORTH);

        return panel;
    }

    
    public static void main(String[] args) throws UnknownHostException, IOException {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new ClientRobiSwing();
    }
}

                    
