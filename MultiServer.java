import java.io.*;
import java.net.*;

public class MultiServer implements Runnable {

	Socket socket;

	public MultiServer(Socket socket) {
		this.socket = socket;
	}

	public void run() {

		try (
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
			BufferedReader stdIn = new BufferedReader(
				new InputStreamReader(System.in));
		){
			String inputLine;
			while((inputLine = in.readLine()) != null){
				MainServer.sendMessage(inputLine, socket);
				// System.out.println("something is wirtten...");
			}
		} catch(Exception e){

		}
	}

	
}