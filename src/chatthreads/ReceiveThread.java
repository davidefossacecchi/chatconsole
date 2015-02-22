package chatthreads;

import java.io.BufferedReader;
import java.net.Socket;

public class ReceiveThread extends Thread {
	private Socket connectionSocket;
	private BufferedReader msgReader;
	private String msg;
	public ReceiveThread(Socket connection, BufferedReader input){
		super();
		this.connectionSocket = connection;
		this.msgReader = input;
		msg = "";
	}
	
	public void run(){
		while(connectionSocket.isConnected()){
			try{
				msg = msgReader.readLine();
				if (msg != null) System.out.println(msg);
				else connectionSocket.close();
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
	}
}
