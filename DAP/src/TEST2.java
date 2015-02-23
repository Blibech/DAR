import java.net.*;
public class TEST2 {
	public static void recherche(int port){
		if(port > 1024);
		else{
			try {
				DatagramSocket sock = new DatagramSocket(port);
				recherche(port+1);
			} catch (SocketException e) {
				System.out.println(port);
				recherche(port+1);
			}
		}
	}
	public static void main(String[] Args){
		recherche(1);
	}
}
