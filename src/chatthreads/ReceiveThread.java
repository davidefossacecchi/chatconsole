package chatthreads;

import java.io.BufferedReader;
import java.net.Socket;

public class ReceiveThread extends Thread {
	private Socket connectionSocket;
	private BufferedReader msgReader;
	
	public ReceiveThread(Socket connection, BufferedReader input){
		super();
		this.connectionSocket = connection;
		this.msgReader = input;
	}
	
	public void run(){
		while(connectionSocket.isConnected()){
			try{
				System.out.println(msgReader.readLine());
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
	}
}
