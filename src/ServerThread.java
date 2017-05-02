import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread{
    Socket socket;
    NextNumberProtocol nnprotocol;
    ServerThread(Socket socket, NextNumberProtocol nnprotocol){
        this.socket = socket;
        this.nnprotocol = nnprotocol;
    }

    public void run(){
        try{
            String message = null;
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader bufferedReader  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("User " + bufferedReader.readLine() + " is now connected to the server.");
            while((message = bufferedReader.readLine()) != null){
                printWriter.println("Server echoing Client message: " + nnprotocol.processInput(message));
            }
            socket.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
