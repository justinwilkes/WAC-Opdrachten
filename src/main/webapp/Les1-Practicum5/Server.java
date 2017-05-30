package practicum5;

import java.net.ServerSocket;
import java.net.Socket;

class Server {
	public static void main(String[] arg) throws Exception {   					
		ServerSocket ss = new ServerSocket(4711);	
		
		while(true) {
			Socket s = ss.accept();
			if(s != null) {				
				MyServlet myservlet = new MyServlet(s); 		
				myservlet.start();		
			}			
		}		
	}
}