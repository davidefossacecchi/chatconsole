package server;

import gui.ChatGUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import chatthreads.*;

public class Server {

	public static void main(String[] args) {
		try{
			ServerSocket serverSocket = new ServerSocket(9999);
			Socket acceptSocket = serverSocket.accept();
			PrintStream output = new PrintStream(acceptSocket.getOutputStream());
			BufferedReader input = new BufferedReader(new InputStreamReader(acceptSocket.getInputStream()));
			ReceiveThread inbox = new ReceiveThread(acceptSocket, input);
			SendThread dispatch = new SendThread(acceptSocket, output);
			ChatGUI gui = new ChatGUI("Server", inbox, dispatch);
			
			inbox.start();
			dispatch.start();
		}
		catch(IOException e){
			e.printStackTrace();
		}

	}

}
