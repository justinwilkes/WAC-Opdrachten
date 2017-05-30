package practicum2;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.InputStream;

public class Server {
	public static void main(String[] arg) throws Exception {	
		ServerSocket ss = new ServerSocket(4711);
		Socket s = ss.accept();
    
		InputStream is = s.getInputStream();
		System.out.println(is);
		Scanner scanner = new Scanner(is);
		System.out.println(scanner.nextLine());
		scanner.close();
		s.close();
		ss.close();
	}
}