import java.io.IOException;
import java.io.*;
import java.net.*;


public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException{
		if(args.length == 1){
			String name = args[0];
			Socket socket = new Socket("localhost", 4444);
			BufferedReader bufferedReaderFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
			printWriter.println(name);
			BufferedReader bufferedReader = new java.io.BufferedReader(new InputStreamReader(System.in));
			int x;
			while(true){
				System.out.println("Please choose the request: ");
				System.out.println("1. Request 5 nextEvenFib values. ");
				System.out.println("2. Request 5 nextLargerRand values. ");
				System.out.println("3. Request 5 nextPrime values. ");
				System.out.println("4. Request All. ");
				String readerInput = bufferedReader.readLine();
				x = Integer.valueOf(readerInput);
				printWriter.println(name + ": " + readerInput);
				System.out.println(bufferedReaderFromClient.readLine());
			}
		}else{
			System.out.println("Usage: Client <name>");
		}
	}
}
