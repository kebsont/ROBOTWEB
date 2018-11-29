import java.net.*;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.Iterator;

class Bot {
    public int urlReader(String url) throws Exception { //Affiche les liens de l'URL
        URL adr = new URL(url);
        int res = 0;
        ArrayList<String> listOfLink = getListLink(adr);
        for(int i=0; i<listOfLink.size(); i++){ //test des liens
            String linkOk = checkLink(listOfLink.get(i));
            System.out.print(i + ": ");
            if((linkOk != "0") && (linkOk != "1")){
                res++;
                System.out.println(linkOk);
            }else{
                System.out.println("    x - " + linkOk);
            }
        }
        System.out.println();
        return (res);
    }

    private ArrayList<String> getListLink(URL url){
        ArrayList<String> listOfLink = new ArrayList<String>();
        try{ //Lecture et extraction des liens
            BufferedReader in = new BufferedReader(
            new InputStreamReader(url.openStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null){ //Remplissage du tableau avec les liens
                String resRegExp = regExp(inputLine);
                if(!resRegExp.equals("")){
                    listOfLink.add(listOfLink.size(), resRegExp);
                }
            }

            // for(int i=0; i<listOfLink.size(); i++){ //Affichage de la liste des liens
                // System.out.print(i + ": ");
                // System.out.println(listOfLink.get(i));
            // }

            in.close();
            return listOfLink;

        }catch(Exception e){
            System.out.println("getListLink - Impossible d'accéder à l'URL");
            return listOfLink;
        }
    }

    private String checkLink(String url) throws Exception{
        URL adr = new URL(url);
        try{ //Lecture et extraction des liens
            BufferedReader in = new BufferedReader(
            new InputStreamReader(adr.openStream()));

            if ((in.readLine()) != null){ //Remplissage du tableau avec les liens
                in.close();
                return url;
            }else{
                return "0";
            }

        }catch(Exception e){
            System.out.println("checkLink - Impossible d'accéder à l'URL");
            return "1";
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
                // String begSlash = codeGroup.substring(0, 1);
                if(begHttp.equals("http")){
                    return (codeGroup);
                // }else if(begSlash.equals("/")){
                //     return (codeGroup);
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
