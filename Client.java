import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

	
	public static void main(String[] args){

		String hostName = "localhost";
		int portNumber = 8888;
		
	
		try(
			Socket socket = new Socket(hostName, portNumber);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
			BufferedReader stdIn = new BufferedReader(
				new InputStreamReader(System.in));
		) {
			new Thread(() -> {
				try {
					String message;
					while((message = in.readLine()) != null){
						System.out.println(">> "+message);
					}	
				} catch(Exception e){

				}
			}).start();
	
			String inputLine;
			while((inputLine = stdIn.readLine()) != null){
				out.println(inputLine);
				out.flush();
			}
		} catch(Exception e) {
			System.err.println(e);
		}
	}
}