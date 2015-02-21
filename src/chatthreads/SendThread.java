package chatthreads;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class SendThread extends Thread {
	
	private Socket connectionSocket;
	private PrintStream sendStream;
	private Scanner scan;
	private String msg = "";
	
	public SendThread(Socket connection, PrintStream output){
		super();
		this.connectionSocket = connection;
		this.sendStream = output;
		this.scan = new Scanner(System.in); 
	}
	
	public void run(){
		while (connectionSocket.isConnected()){
			msg = scan.nextLine();
			sendStream.println(msg);
		}
	}

}
