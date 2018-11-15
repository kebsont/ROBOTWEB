import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.*;


public class IOCommandes{
  private BufferedReader lectureEcran;
  private PrintStream ecritureEcran;

  private BufferedReader lectureReseau;
  private PrintWriter ecritureReseau;
  private BufferedWriter ecritureLog;
  private Socket socket;
  private String fileName = "log.txt";

  public IOCommandes(Socket soc){
    try
    {
      this.lectureEcran =  new BufferedReader(new InputStreamReader(System.in));
      this.ecritureEcran = System.out;
      this.lectureReseau =  new BufferedReader(new InputStreamReader(soc.getInputStream())); //in
      this.ecritureReseau = new PrintWriter(soc.getOutputStream(), true);//out
      FileWriter fileWriter =  new FileWriter(fileName, true);
      this.ecritureLog = new BufferedWriter(fileWriter);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

  }

  public void ecrireEcran(String texte){
    ecritureEcran.println(texte);
  }

  public String lireEcran() throws IOException{

      return lectureEcran.readLine();

  }

  public void ecrireReseau(String texte){
    ecritureReseau.println(texte);
  }

  public String lireReseau() throws IOException{

      return lectureReseau.readLine();

  }
  public void ecrireLog(String texte)throws IOException{
     ecritureLog.write(texte);
     ecritureLog.flush();
  }
  public void fermeLog(){
    try {
        ecritureLog.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
  }

  public void stopConnexion(){
      try {
          lectureEcran.close();
      } catch (IOException e) {
          e.printStackTrace();
      }
      ecritureEcran.close();
      try {
          socket.close();
      } catch (IOException e) {
          e.printStackTrace();
      }
      try {
          ecritureLog.close();
      } catch (IOException e) {
          e.printStackTrace();
      }

  }
}
