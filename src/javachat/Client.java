package javachat;

import java.io.*;
import java.net.*;

public class Client {

	public static void main(String[] args) throws Exception{
		
		boolean connected = false;
		int PORT = -1;
		InetAddress addr = null;
		Socket socket = null;
		
		try {
			addr = InetAddress.getByName(args[0]);
			PORT = Integer.parseInt(args[1]);
			System.out.println("Server Address = " + addr);
			socket = new Socket(addr, PORT);
			System.out.println("Connection Succeed.");
			connected = true;
		}catch (SocketException se) {
			se.printStackTrace();
			connected = false;
		}finally {
			
		}
		
		if(!connected) return;
		
		try {
			//user_in: ユーザーからの入力を受ける
			//user_out: ユーザへの出力を送る
			BufferedReader user_in = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter user_out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);
			//server_in: サーバからの入力を受ける
			//user_out: サーバへの出力を送る
			BufferedReader server_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter server_out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			//送信用スレッドと受信用スレッドを作成
			ClientThread sender = new ClientThreadSender(user_in, server_out, user_out);
			ClientThread receiver = new ClientThreadReceiver(server_in, user_out, null);
			//スレッドの開始
			sender.start();
			receiver.start();
			//スレッドの終了を待つ
			sender.join();
			receiver.join();
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}finally {
			//System.out.println("closing...");
			socket.close();
		}
	}

}
