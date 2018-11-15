import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class Server {
  private long minServer;
  private ServerSocket servSock = null;
  private Socket ClientSock;
  private IOCommandes obj;
  private String texte;
  private String host = "127.0.0.1";
  private int port = 1445;
  private static final int MAXCONN = 2;
  private boolean enMarche = true;



  public Server(){
   try {
      servSock = new ServerSocket(port, 2, InetAddress.getByName(host));
   } catch (UnknownHostException e) {
      e.printStackTrace();
   } catch (IOException e) {
      e.printStackTrace();
   }
}

public Server(String pHost, int pPort){
   host = pHost;
   port = pPort;
   try {
      servSock = new ServerSocket(port, 2, InetAddress.getByName(host));
   } catch (UnknownHostException e) {
      e.printStackTrace();
   } catch (IOException e) {
      e.printStackTrace();
   }
}

public void lance(){

   Thread t = new Thread(new Runnable(){
        public void run(){
        while(enMarche == true){
           try {
                ClientSock = servSock.accept();
                System.out.println("Connexion cliente reçue.");
                 Thread t = new Thread(new TraitementClient(ClientSock));
                 t.start();
           }catch (IOException e) {
                  e.printStackTrace();
               }
             }
             try {
                     servSock.close();
                  } catch (IOException e) {
                     e.printStackTrace();
                     servSock = null;
                  }
        }
});
t.start();
}

public void close(){
      enMarche = false;
   }

public int getPort() {
  return servSock.getLocalPort();
}





}
