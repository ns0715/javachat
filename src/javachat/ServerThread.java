package javachat;

import java.io.*;
import java.net.*;
import java.util.regex.*;

public class ServerThread extends Thread {
	
	int threadID = 0;
	String clientName = null;
	Socket socket = null;
	private BufferedReader in = null;
	private PrintWriter out = null;
	
	public ServerThread (int threadID, Socket socket) throws IOException {
		this.threadID = threadID;
		this.socket = socket;
		this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream())), true);;
	}
	
	public void run() {
		try {
			String inputData = null;
			//名前情報の取得
			/*
			inputData = in.readLine();
			clientName = Pattern.compile("command=name=").matcher(inputData).replaceFirst("");
			Server.sendAll("[Server]: " + clientName + " has joined.");
			*/
			//入力を1行読み込んで出力
			while((inputData = in.readLine()) != null) {
				/*
				if(inputData.matches("command=.+")) {
					if(inputData.equals("command=exit")) {
						send("command=exit");
						break;
					}else if(inputData.matches("command=name=.+")){
						clientName = Pattern.compile("command=name=").matcher(inputData).replaceFirst("");
						send("Your name is " + clientName + ".");
					}
				}else if(inputData.matches("message=.+")) {
					Server.sendAll("[" + clientName + "] : " + Pattern.compile("message=").matcher(inputData).replaceFirst(""));
				}
				//Server.sendAll("[" + threadID + "]" + clientName + ": " + inputData);		//受け取った入力をServerクラスに返してからsendAllメソッドで全クライアントに送信
				*/
				Server.sendAll(inputData);
				
			}
			//Server.remove(this);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				out.close();
				in.close();
				socket.close();
			}catch(IOException ioe) {
				ioe.printStackTrace();
			}finally {
				
			}
		}
	}
	
	public void send(String inputData) {
		out.println(inputData);
	}
	
	
}
