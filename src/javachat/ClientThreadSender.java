package javachat;

import java.io.*;

public class ClientThreadSender extends ClientThread {

	ClientThreadSender(BufferedReader in, PrintWriter out, PrintWriter log) {
		super(in, out, log);
	}
	
	void init() throws IOException {
		log.println("Enter your name ... ");
		inputData = in.readLine();
		out.println("command=name=" + inputData);
	}

	void parse() throws IOException {
		while ((inputData = in.readLine()) != null) {
			if (inputData.equals("!exit")) {
				out.println("command=exit");
				break;
			} else if (inputData.equals("!name")) {
				log.println("Enter your name ...");
				inputData = in.readLine();
				out.println("command=name=" + inputData);
			} else if (inputData.equals("!help")) {
				log.println("!help : show command list");
				log.println("!name : change your name");
				log.println("!exit : exit the room");
			} else {
				out.println("message=" + inputData);
			}
		}
	}

}
