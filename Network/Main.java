import java.net.Socket;
import java.net.ServerSocket;
public class Main{
  public static void main(String[] args) throws Exception{
    String host = "192.168.200.146";
     int port = 1445;
    Server monServeur = new Server(host, port);
    monServeur.lance();
    // //new Thread().start();
  }
}
