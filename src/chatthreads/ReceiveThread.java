package chatthreads;

import java.io.BufferedReader;
import java.net.Socket;
import java.util.HashSet;


import javax.swing.event.EventListenerList;

public class ReceiveThread extends Thread {
	private Socket connectionSocket;
	private BufferedReader msgReader;
	private String msg;
	private HashSet<InboxListener> chatListener = new HashSet<InboxListener>();
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
				if (msg != null) msgReceived(msg);
				else connectionSocket.close();
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void addInboxListener(InboxListener listener){
		chatListener.add(listener);
	}
	
	public void removeInboxListener(InboxListener listener){
		chatListener.remove(listener);
	}
	
	protected void msgReceived(String msg){
		for(InboxListener listener: chatListener) listener.messageReceived(msg);
	}
}
