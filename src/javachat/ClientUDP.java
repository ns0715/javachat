package javachat;

import java.io.*;
import java.net.*;


public class ClientUDP {
	
	public static void main(String[] args) throws Exception{
		
		try {
			InetAddress addr = InetAddress.getByName(args[0]);
			int PORT = Integer.parseInt(args[1]);
			BufferedReader user_in = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter user_out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);
			ClientThread sender = new ClientThreadSenderUDP(user_in, user_out, addr, PORT);
			ClientThread receiver = new ClientThreadReceiverUDP(user_in, user_out, addr, PORT + 1);
			sender.start();
			receiver.start();
			sender.join();
			receiver.join();
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}finally {

		}
		
	}

}
