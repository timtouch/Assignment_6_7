import java.io.*;
import java.net.*;
import java.lang.*;

public class Server{

	public static final int PORT = 4444;

	public static void main(String[] args) throws IOException{
		ServerSocket serverSocket = new ServerSocket(PORT);
		NextNumberProtocol nnprotocol = new NextNumberProtocol();
		System.out.println("Server up and ready for connections...");

		try {
			while (true) {
				Socket socket = serverSocket.accept();
				new ServerThread(socket, nnprotocol).start();
			}
		} finally {
			serverSocket.close();
		}
	}

}
