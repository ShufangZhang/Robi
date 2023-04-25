package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class AnsweringMachine extends Thread {

	ServerSocket serv;
	Socket sock;
	BufferedReader br;
	PrintStream ps;
	Thread th;

	public AnsweringMachine(ServerSocket serv, Thread th) {
		this.serv = serv;
		this.th = th;
	}

	@Override
	public void run() {
		String client_message;
		while (true) {
			try {
				this.sock = serv.accept();
				this.br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				this.ps = new PrintStream(sock.getOutputStream(), true);
				client_message = this.br.readLine();
				if (client_message.equals("stop")) {
					this.th.interrupt();
					System.out.println("Program has been interrupted");
				} else {
					this.ps.println("Parser is busy");
				}
			} catch (IOException e) {
				return;
			}
		}
	}
}
