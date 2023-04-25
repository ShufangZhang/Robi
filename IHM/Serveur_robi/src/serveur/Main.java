package serveur;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	static int port = 8000;
	static ServerSocket robiServer;
	static Socket socket;
	static BufferedReader socket_bufferedReader;
	static PrintStream socket_printStream;
	static String program = "";

	public static void main(String[] args) throws Exception {

		System.out.println("Serveur Robi démarré...");

		InitSpace initializer = new InitSpace();
		Thread parser_thread;
		Thread answer_thread;
		int received_char;

		while (true) {
			try {
				robiServer = new ServerSocket(port);
				// attente d'un client
				System.out.println("Serveur en attente d'un client...");
				socket = robiServer.accept();
				socket_bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				socket_printStream = new PrintStream(socket.getOutputStream(), true);

				System.out.println("Attente du programme ROBI...");

				program = "";
				while ((received_char = socket_bufferedReader.read()) != '\0') {
					System.out.print((char) received_char);
					program = program + (char) received_char;
				}

				System.out.println("Programme reçu :");
				System.out.println(program);
				socket_printStream.println("Programme reçu");

				// Program reçu
				socket.close();

				// Creation des threads

				parser_thread = new ParserCall(program, initializer.environment);
				answer_thread = new AnsweringMachine(robiServer, parser_thread);
				answer_thread.start();
				parser_thread.start();
				parser_thread.join();
				System.out.println("Fin d'exécution du programme ROBI");

				robiServer.close();

			} catch (Exception e) {
				System.out.println("Erreur : " + e.getMessage());
			}
		}
	}
}
