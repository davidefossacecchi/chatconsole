package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import chatthreads.*;

public class Client {
	public static void main(String[] args) {
		try{
			Socket clientSocket = new Socket ("127.0.0.1", 9999);
			PrintStream output = new PrintStream(clientSocket.getOutputStream());
			BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			ReceiveThread inbox = new ReceiveThread(clientSocket, input);
			SendThread dispatch = new SendThread(clientSocket, output);
			
			inbox.start();
			dispatch.start();
		}
		catch (IOException e){
			e.printStackTrace();
		}

	}

}
