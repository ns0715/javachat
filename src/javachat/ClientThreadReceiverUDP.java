package javachat;

import java.io.*;
import java.net.*;

public class ClientThreadReceiverUDP extends ClientThread {
	
	InetAddress addr = null;
	int PORT = -1;
	DatagramPacket dp = null;
	DatagramSocket ds= null;
	byte[] inputData = null;
	
	ClientThreadReceiverUDP(BufferedReader in, PrintWriter out, InetAddress addr, int PORT){
		super(in, out, null);
		this.addr = addr;
		this.PORT = PORT;
	}
	
	void init() throws IOException {
		
	}
	
	void parse() throws IOException {
		try{
			while(true){
				byte[] buffer = new byte[128];
				dp = new DatagramPacket(buffer, buffer.length);
				ds = new DatagramSocket(PORT);
				ds.setReuseAddress(true);
				ds.receive(dp);
				String str = new String(dp.getData());
				out.println(str);
				ds.close();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
	}
	
	
}
