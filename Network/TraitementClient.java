import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetSocketAddress;
import java.text.DateFormat;
import java.util.Date;
import java.text.*;
import java.io.*;
 public class TraitementClient implements Runnable {
     private Socket clientSock;
     private IOCommandes obj;
     private String texte;
     private int port = 1445;
     private static final int MAXCONN = 2;
     ///public static File log =  new File("path/de/ton/fichier");
     private boolean enMarche = true;

     public TraitementClient(Socket clientsocket){
       clientSock = clientsocket;
     }

        public void run(){
           boolean closeConnexion = false;
           Date dNow = new Date( );
            SimpleDateFormat ft =
            new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
           //tant que la connexion est active, on traite les demandes
           while(!clientSock.isClosed()){
              try {
                InetSocketAddress remote = (InetSocketAddress)clientSock.getRemoteSocketAddress();
                obj = new IOCommandes(clientSock);
                // instantiate the Bot
                Bot bot = new Bot();
                String compare = ">";
                obj.ecrireReseau("Bienvenue je suis votre Bot. On m'appelle R0B0TW3B");
                texte = obj.lireReseau(); // lire ce qu'ecrit l'utilisateur
                  while(!texte.equals("quit")){
                    obj.ecrireEcran(texte);
                    String output = String.format("%s",  texte);   //formatter l'input de l'utilisateur pour le mettre dans des guillements                  // obj.ecrireEcran(
                    try{
                      bot.urlReader(output); // Lire l'url
                    }catch (Exception e) { // ou si c'est pas un bon url, renvoyer l'erreur à l'utilisateur
                      obj.ecrireReseau(e.toString());
                        e.printStackTrace();
                    }
                    texte = obj.lireReseau();//réecouter l'utilisateur

                if (texte.equals("quit"))
                  obj.stopConnexion();
                }
              }catch(IOException e){
            System.err.println("LA CONNEXION A ETE INTERROMPUE ! ");
            break;
                }
           }
        }




     ///
 }
