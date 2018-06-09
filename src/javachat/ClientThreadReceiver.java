package javachat;

import java.io.*;

public class ClientThreadReceiver extends ClientThread {
	
	ClientThreadReceiver(BufferedReader in, PrintWriter out, PrintWriter log) {
		super(in, out, log);
	}
	
	void init() throws IOException {
		
	}

	void parse() throws IOException {
		while ((inputData = in.readLine()) != null) {
			//コマンドの判定
			if(inputData.matches("command=.+")) {
				if(inputData.equals("command=exit")) {
					break;
				}
			}else {
				out.println(inputData);
			}
		}
	}

}
