import java.net.*;
import java.io.*;

public class Client {
	public int port;
	public InetAddress adr;
	public String msg;
	public byte[] tampon;
	
	public Client(){
		port = 5000;
		adr = null;
		msg = "Salut!,mode Datagramme en illustration";
		tampon = new byte[msg.length()];
	}
	
	public String WriteMsg()throws IOException{
		System.out.print("Votre message : ");
		BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
		return clavier.readLine();
	}

        public String WriteIP()throws IOException{
		System.out.print("IP de Host : ");
		BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
		return clavier.readLine();
	}
	
	public void envoyer(String adresse,String msg)throws IOException,SocketException,NumberFormatException{
		try{
			adr = InetAddress.getByName(adresse);
		}catch(UnknownHostException e){
			System.out.println("Adresse Introuvable !");
			System.exit(2);
		}
		tampon = msg.getBytes();
		DatagramPacket paquet = new DatagramPacket(tampon, tampon.length,adr,port);
		DatagramSocket sock = new DatagramSocket();
		sock.send(paquet);
		sock.close();
        System.out.println("(Envoyer à "+adr.getHostName()+" IP:"+adr.getHostAddress()+")");
	}
	
	public void recevoire() throws IOException{
		byte[] tampon = new byte[200];
		String msg;
		DatagramSocket sock = new DatagramSocket(5010);
		DatagramPacket paquet = new DatagramPacket(tampon, tampon.length);
		sock.receive(paquet);
		msg = new String(tampon);
		System.out.println("Host ("+adr.getHostName()+" IP:"+adr.getHostAddress()+") : "+msg);
		sock.close();
	}
	
	public static void main(String[] args) throws IOException{
		Client cl = new Client();
                System.out.println("** Client **");
                String IP = cl.WriteIP();
		while(true){
			try {
				cl.envoyer(IP, cl.WriteMsg());
				cl.recevoire();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}