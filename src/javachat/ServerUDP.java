package javachat;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerUDP {
	
	static int PORT = -1;
	static PrintWriter log = null;
	static Set<InetAddress> addressList = new HashSet<InetAddress>();
	
	public static void main(String[] args) throws Exception{
		
		PORT = Integer.parseInt(args[0]);
		DatagramPacket dp = null;
		DatagramSocket ds = null;
		log = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);
		
		try {
			while(true) {
				byte[] buffer = new byte[128];
				dp = new DatagramPacket(buffer, buffer.length);
				ds = new DatagramSocket(PORT);
				ds.receive(dp);
				//受信後
				addressList.add(dp.getAddress());
				log.println(dp.getAddress());
				ds.close();
				sendAll(dp.getData());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ds.close();
		}
	}
	
	public static synchronized void sendAll(byte[] inputData) {
		DatagramPacket dp = null;
		DatagramSocket ds = null;
		try{
			for(InetAddress addr : addressList) {
				dp = new DatagramPacket(inputData, inputData.length, addr, PORT + 1);
				ds = new DatagramSocket();
				ds.send(dp);
				ds.close();
			}
			log.println(inputData);
			log.println(new String(inputData));
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ds.close();
		}
	}
	
	public static void remove(InetAddress left) {
		addressList.remove(left);
	}

}
