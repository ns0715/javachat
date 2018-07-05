package javachat;

import java.io.*;
import java.net.*;

public class ClientThreadSenderUDP extends ClientThread {
	
	InetAddress addr = null;
	int PORT = -1;
	DatagramPacket dp = null;
	DatagramSocket ds= null;
	
	ClientThreadSenderUDP(BufferedReader in, PrintWriter out, InetAddress addr, int PORT){
		super(in, out, null);
		this.addr = addr;
		this.PORT = PORT;
	}
	
	void init() throws IOException {
		
	}
	
	void parse() throws IOException {
		try {
			while((inputData = in.readLine()) != null) {
				byte[] buffer = inputData.getBytes();
				dp = new DatagramPacket(buffer, buffer.length, addr, PORT);
				ds = new DatagramSocket();
				ds.setReuseAddress(true);
				ds.send(dp);
				ds.close();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ds.close();
		}
	}
	

}
