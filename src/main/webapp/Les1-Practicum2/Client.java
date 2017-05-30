package practicum2;

import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Client {
	public static void main(String[] arg) throws Exception {
		Socket s = new Socket("145.89.84.112", 4711);
		OutputStream os = s.getOutputStream();
		PrintWriter pw = new PrintWriter(os, true);
		pw.write("Test");
		pw.close();
		s.close();
	}
}
