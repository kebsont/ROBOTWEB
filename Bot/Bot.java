import java.net.*;
import java.io.*;
class Bot {
    public int urlReader(String url) throws Exception { //Affiche le contenu de l'adresse URL (ligne par ligne)
        URL oracle = new URL(url);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null){
                System.out.println(inputLine);
        }
        in.close();
        return 0;
    }
}
