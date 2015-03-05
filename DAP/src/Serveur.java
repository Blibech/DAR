import java.net.*;
import java.io.*;

public class Serveur {
	public int port;
	public InetAddress adr;
	public String msg;
	public byte[] tampon;
	//hghghghghghghgh
	public Serveur(){
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
		DatagramPacket paquet = new DatagramPacket(tampon, tampon.length,adr,5010);
		DatagramSocket sock = new DatagramSocket();
		sock.send(paquet);
		sock.close();
        System.out.println("(Envoyer ра "+adr.getHostName()+" IP:"+adr.getHostAddress()+")");
        
        // getHostAddress() => IP sous forme String
        // getHostName() => nom du machine sous forme String
        // getAddress() => IP sous forme Byte[]
	}
	
	public void recevoire() throws IOException{
		byte[] tampon = new byte[200];
		String msg;
		DatagramSocket sock = new DatagramSocket(port);
		DatagramPacket paquet = new DatagramPacket(tampon, tampon.length);
		sock.receive(paquet);
        adr = paquet.getAddress();
		msg = new String(tampon);
		System.out.println("Host ("+adr.getHostName()+" IP:"+adr.getHostAddress()+") : "+msg);
		sock.close();
	}
	
	public static void main(String[] args){
		Serveur cl = new Serveur();
                System.out.println("** Serveur **");
		while(true){
			try {
				cl.recevoire();
				cl.envoyer(cl.adr.getHostAddress(), cl.WriteMsg());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}