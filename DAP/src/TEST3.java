import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
public class TEST3 {
	public static void main(String[] Args) throws IOException{
		Socket s = new Socket("www.google.com",80);
		System.out.println(s.getLocalAddress().getHostAddress());
	}
}
