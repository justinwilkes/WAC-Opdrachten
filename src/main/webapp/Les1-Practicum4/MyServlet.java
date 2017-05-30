package practicum4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MyServlet extends Thread {
	private Socket socket;
	
	public MyServlet(Socket sock) {
		this.socket = sock;
	}
	
	public void run() {			
		try {
			InputStream is = this.socket.getInputStream();
			Scanner scanner = new Scanner(is);
		    
		    while(scanner.hasNextLine()){
		    	String in = scanner.nextLine();
		    	if(in.equals("")) break;
		    	else System.out.println(in);    	
		    }

			OutputStream os = this.socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);
			pw.write("HTTP/1.1 200 OK");
		  	pw.write("");
		  	pw.write("SUCCES");	
		  	pw.close();
		   		    
		    scanner.close();
		    this.socket.close();			
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
}
