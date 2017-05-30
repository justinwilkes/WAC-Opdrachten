package practicum3;

import java.net.Socket;
import java.util.Scanner;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Client {
	public static void main(String[] arg) throws Exception {
		String String;
		Socket s = new Socket("82.72.229.45", 4711);   
		OutputStream os = s.getOutputStream();
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(os, true);
		
		while(String != "stop") {
			String = sc.nextLine();
			pw.println(String);
		}		
	
		pw.close();
		s.close();
		sc.close();
	}
}