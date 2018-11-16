import java.net.*;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Bot {
    public int urlReader(String url) throws Exception { //Affiche les liens de l'URL
        URL oracle = new URL(url);
        try{ //Lecture et extraction des liens
            BufferedReader in = new BufferedReader(
            new InputStreamReader(oracle.openStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null){
                System.out.print(regExp(inputLine));
            }

            in.close();
            return 0;

        }catch(Exception e){
            System.out.println("Impossible d'accéder à l'URL");
            return 1;
        }
    }

    private String regExp(String line){
        //Création de la RegEx
        Pattern p = Pattern.compile("(.*?)<a(.?)href=\"(.+)\" (.*?)>(.+)</a>(.*?)");
        Matcher m = p.matcher(line);

        //Extraction de la chaine de caractère (liens)
        if (m.find()) {
            // get the matching group
            String codeGroup = m.group(3);
            codeGroup = codeGroup.split("\"")[0];

            //Tri des lien (uniquement "http... && /...")
            if(codeGroup.length()>4){
                String begHttp = codeGroup.substring(0, 4);
                String begSlash = codeGroup.substring(0, 1);
                if(begHttp.equals("http")){
                    // System.out.println("a"+begHttp+"b");
                    return (codeGroup + "\n");
                }else if(begSlash.equals("/")){
                    return (codeGroup + "\n");
                }else{
                    return "";
                }
            }else{
                return "";
            }
        }else{
            return "";
        }
    }
}
