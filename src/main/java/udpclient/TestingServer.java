package udpclient;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class TestingServer {
	
	final static int PORT   = 6000;
	final static int TAILLE = 1024;
	static byte BUFFER[]    = new byte[TAILLE];

	public static void main(String[] args) {
		while (true) {
			try {
				System.out.println("Calling server... ");
			    InetAddress serveur = InetAddress.getByName("192.168.0.88");
			      
			    String message = "Radouane";
			    int  LENGTH     = message.length();
			    byte BUFFER[]  = message.getBytes();
			      
			    DatagramSocket socket        = new DatagramSocket();
			    DatagramPacket donneesEmises = new DatagramPacket(BUFFER, LENGTH, serveur, PORT);
			    DatagramPacket donneesRecues = new DatagramPacket(new byte[TAILLE], TAILLE);
	
			    socket.setSoTimeout(30000);
			    socket.send(donneesEmises);
			    socket.receive(donneesRecues);
	
			    System.out.println("Message : " + new String(donneesRecues.getData(), 0, donneesRecues.getLength()));
			    System.out.println("de : " + donneesRecues.getAddress() + ":" + donneesRecues.getPort());
			      
			    Thread.sleep(500);
		    } catch (SocketTimeoutException ste) {
		    	System.out.println("Le delai pour la reponse a expire");
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		}
		
	}

}
