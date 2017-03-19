import java.io.*;
import java.net.*;
import java.util.*;

public class MainServer {

	public static List<Socket> clients = new ArrayList();
	
	public static void main(String[] args) {

		int portNumber = 8888;

		try (
			ServerSocket serverSocket = new ServerSocket(portNumber);
		) {
			while(true){
				System.out.println("... Waiting for connection ...");
				Socket newConnection = serverSocket.accept();
				clients.add(newConnection);
				new Thread(new MultiServer(newConnection)).start();
				System.out.println("new connection ... " + newConnection.toString());
			}
		} catch(Exception e){
			System.err.println(e);
			System.exit(1);
		}
	}

	public static void sendMessage(String l, Socket s) {
		PrintWriter out;
		for(Socket ss : clients){
			if(ss != s){
				try {
					out = new PrintWriter(ss.getOutputStream(), true);
					out.println(l);
					System.out.println("From["+s.toString()+
						"] to ["+ss.toString()+"]:\n_" + l);
				} catch(Exception e){

				}
			}
		}
	}
}