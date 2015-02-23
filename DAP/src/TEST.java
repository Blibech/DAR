import java.net.*;
public class TEST {
	public static void main(String[] Args) throws UnknownHostException{
		InetAddress[] Adrs = InetAddress.getAllByName("www.google.com");
		for (int i = 0;i<Adrs.length;i++){
			System.out.println(Adrs[i].getHostAddress());
		}
	}
}
