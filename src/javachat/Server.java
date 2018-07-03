package javachat;

import java.util.*;
import java.io.*;
import java.net.*;

public class Server {

	static PrintWriter log = null;
	static ArrayList<ServerThread> threadList = new ArrayList<ServerThread>();

	public static void main(String[] args) throws Exception {

		int PORT = Integer.parseInt(args[0]);
		int num_member = 0;

		BufferedReader user_in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter user_out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);
		log = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);
		
		ServerSocket serversocket = new ServerSocket(PORT);
		log.println("Started: " + serversocket);
		try {
			while (true) {
				ServerThread newthread = null;
				// クライアントからの接続要求を承認
				Socket socket = serversocket.accept();
				num_member++;
				log.println("Connection Accepted: " + socket);
				try {
					// クライアントと通信を行うスレッドを新規作成
					newthread = new ServerThread (num_member, socket);
					threadList.add(newthread);
					log.println("Thread " + num_member + " created.");
					newthread.start();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} finally {

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			user_in.close();
			user_out.close();
			serversocket.close();
		}
	}

	public static synchronized void sendAll(String inputData) {
		for (ServerThread threads : threadList) {
			if (threads != null)
				threads.send(inputData);
		}
		log.println(inputData);
	}

	public static void remove(ServerThread left) {
		threadList.remove(left); // 退出したクライアントと通信していたスレッドをリストから削除
		//sendAll("[Server]: " + left.clientName + " has left the room.");
	}

}
