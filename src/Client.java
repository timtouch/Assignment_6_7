import java.io.IOException;

/**
 * Top level class that starts the user threads
 */
public class Client {


	public static void main(String[] args) throws IOException{
		if(args.length == 2){
			int numOfUsers = Integer.parseInt(args[0]);
			String IPAddress = args[1];
			try {
				for (int i = 0; i < numOfUsers; i++) {
					new UserThread(i,20, new RuntimeThread(i, IPAddress)).start();
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		} else {
			System.out.println("Usage: Client <Number of UserThreads> <Server IP Address>");
		}
	}
}
