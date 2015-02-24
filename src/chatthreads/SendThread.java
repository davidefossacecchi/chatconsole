package chatthreads;

import java.io.PrintStream;
import java.net.Socket;
import java.util.LinkedList;

public class SendThread extends Thread {
	
	private Socket connectionSocket;
	private PrintStream sendStream;
	private String msg = "";
	private LinkedList<String> msgs;
	
	public SendThread(Socket connection, PrintStream output){
		super();
		this.connectionSocket = connection;
		this.sendStream = output;
		this.msgs = new LinkedList<String>();
	}
	
	public void run(){
		while (connectionSocket.isConnected()){
			if(msgs.size() > 0) sendStream.println(msgs.poll());
		}
	}
	
	public void sendMessage(String msg){
		msgs.add(msg);
	}

}
