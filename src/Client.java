import java.io.IOException;
import java.io.*;
import java.net.*;


public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException{
		if(args.length == 2){
			String name = args[0];
			String IPAddress = args[1];
			Socket socket = new Socket(IPAddress, 4444);
			BufferedReader bufferedReaderFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
			printWriter.println(name);
			BufferedReader bufferedReader = new java.io.BufferedReader(new InputStreamReader(System.in));
			int x;
			while(true){
				System.out.println("Please type any character to send a request: ");
				String readerInput = bufferedReader.readLine();
				x = Integer.valueOf(readerInput);
				printWriter.println(name + ": " + readerInput);
				System.out.println(bufferedReaderFromClient.readLine());
			}
		} else {
			System.out.println("Usage: Client <name> <Server IP Address>");
		}
	}
}
