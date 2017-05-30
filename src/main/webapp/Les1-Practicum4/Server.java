package practicum4;

import java.net.ServerSocket;
import java.net.Socket;

class Server {
	public static void main(String[] arg) throws Exception {   					
		ServerSocket ss = new ServerSocket(4711);			
		Socket s = ss.accept(); 		
		MyServlet myservlet = new MyServlet(s); 		
		myservlet.start();			
	}
}