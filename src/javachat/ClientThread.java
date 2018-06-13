package javachat;

import java.io.*;

public abstract class ClientThread extends Thread {
	
	//logはユーザーへのログ表示用ストリーム
	BufferedReader in = null;
	PrintWriter out = null;
	PrintWriter log = null;
	String inputData = null;
	
	//コンストラクタ
	ClientThread(BufferedReader in, PrintWriter out, PrintWriter log){
		this.in = in;
		this.out = out;
		this.log = log;
	}
	
	public void run(){
		try {
			//初期化
			//init();
			//入力を1行読み込んで出力
			parse();
			//exitコマンドが入力されるまでループを続行
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}finally {
			/*
			try {
				in.close();
				out.close();
			}catch (IOException ioe) {
				ioe.printStackTrace();
			}finally {
				
			}*/
		}
		
	}
	
	abstract void init () throws IOException;
	
	abstract void parse () throws IOException;

}
