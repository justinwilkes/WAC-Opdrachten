package practicum1;

import java.net.Socket;
import java.io.OutputStream;

class Client {
  public static void main(String[] arg) throws Exception {
    Socket s = new Socket("145.89.84.112", 4711);
    OutputStream os = s.getOutputStream();
    os.write("Response: ".getBytes());
    s.close();
  }
}