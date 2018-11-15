
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetSocketAddress;
import java.text.DateFormat;
import java.util.Date;
import java.text.*;


 public class TraitementClient implements Runnable{
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
                // base = new Bdd();
                String compare = ">";
                obj.ecrireReseau("Bienvenue sur le Serveur de Chat, veuillez vous identifier en tapant login<prenom> sans les chevrons, suivi de votre login");
                texte = obj.lireReseau();
                  while(!texte.equals("quit")){
                  if(texte.startsWith("login<")) {
                    //a voir pk je suis obligé de passer par getUser pour mon for
                    // obj.ecrireReseau("il ya lignes: "+base.getCount());
                    //ne pas oublier de faire la verification complete de la saisie des chevrons
                    String login_texte = texte.substring(texte.indexOf('<')+1, texte.indexOf('>'));
                    // obj.ecrireReseau("login_texte avant for : "+base.getUsers(1));
                    // obj.ecrireReseau("login_texte avant for : "+base.getUsers(2));
                    // obj.ecrireReseau("login_texte avant for : "+base.getUsers(3));
                      // for (int i = 1;i<=base.getCount() ;i++ ) {
                        // obj.ecrireReseau("login_texte adns for "+base.getUsers(i));
                        // obj.ecrireReseau(Integer.toString(i));
                        // obj.ecrireReseau("voici "+base.getUsers(i));
                        // if(login_texte.equals(base.getUsers(i))){
                        //   obj.ecrireReseau("Bienvenue " + base.getUsers(i));
                        // }else{
                        //   obj.ecrireReseau("Utilisateur inconnu");
                        //   }
                        // }
                }else{
                  obj.ecrireReseau("Le substring : "+texte.substring(texte.length()-1));
                  obj.ecrireReseau("Tapez login<prenom> sans les chevrons, suivi de votre login");
                  texte = obj.lireReseau();
                }

              /*  obj.ecrireEcran("Client: " + texte);
                obj.ecrireReseau("Vous avez ecrit: "+ texte);
                String log = "";
                 log += "Adresse IP : " + remote.getAddress().getHostAddress() +" ";
                 log += "-  Date et heure : " + ft.format(dNow) + " ";
                 log += "\t***\t Message : " + texte + "\n";
                 obj.ecrireLog(log);
                 //obj.fermeLog();
                 */


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
