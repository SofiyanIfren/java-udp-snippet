package udpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Launcher {
	
	final static int PORT  = 6000;
	final static int TAILLE = 1024;
	static byte BUFFER[] = new byte[TAILLE];

	public static void main(String[] args) {
        try {
			DatagramSocket socket = new DatagramSocket(PORT);
			System.out.println("Lancement du serveur.....");
			
			String donnees = "";
		    String message = "";
		    int taille     = 0;
		    
			while (true) {
			      DatagramPacket paquet = new DatagramPacket(BUFFER, BUFFER.length);
			      DatagramPacket envoi = null;
			      socket.receive(paquet);

			      System.out.println("\n"+paquet.getAddress());
			      taille = paquet.getLength();
			      donnees = new String(paquet.getData(),0, taille);
			      System.out.println("Donnees reçues = "+donnees);

			      message = "Bonjour "+donnees;
			      System.out.println("Donnees envoyees = "+message);
			      envoi = new DatagramPacket(message.getBytes(), 
			      message.length(), paquet.getAddress(), paquet.getPort());
			      socket.send(envoi);
			    }
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
