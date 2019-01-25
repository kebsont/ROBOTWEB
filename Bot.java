import java.net.*;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.Iterator;

public class Bot {

    public ArrayList<String> urlReader(String url, int lvl) throws Exception { //Affiche les liens de l'URL
        URL adr = new URL(url);
        // int it = 0;
        ArrayList<String> listOfLink = getListLink(adr);
        ArrayList<String> listRes = new ArrayList<String>();
        ArrayList<String> fullLinks = new ArrayList<String>();
        // ArrayList<ArrayList<String>> listULinks = new ArrayList<ArrayList<String>>();
        // return getListLink(adr);
        for(int i=0; i<listOfLink.size(); i++){ //test des liens
            String linkOk = checkLink(listOfLink.get(i));
            if((linkOk != "0") && (linkOk != "1")){
                fullLinks.add(linkOk);
                listRes.add(linkOk);
                // System.out.println(linkOk);
                // it++;
            }else{

            }
        }
        if(lvl>0){
            int siz = listRes.size();
            for(int i=0; i<siz; i++){
                ArrayList<String> tmp = this.urlReader(listRes.get(i), lvl-1);
                // listULinks.add(tmp);
                for(int j=0; j<tmp.size(); j++){
                    listRes.add(tmp.get(j));
                }
            }
        }
        System.out.println();
        ArrayList<String> listDom = getDomain(listRes);
        return (listDom);
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

    private ArrayList<String> getDomain(ArrayList<String> lisUrl){
        ArrayList<String> domainList = new ArrayList<String>();
        ArrayList<Integer> score = new ArrayList<Integer>();
        for(int i=0; i<lisUrl.size(); i++){
            // System.out.println(lisUrl.size());
            URL adr;
            try{
                adr = new URL(lisUrl.get(i));
                //more code goes here
                String domain =  adr.getHost();
                String[] masterDom = domain.split("\\.");
                domain = masterDom[masterDom.length-2] + "." + masterDom[masterDom.length-1];
                // System.out.println(masterDom[masterDom.length-2] + "." + masterDom[masterDom.length-1]);
                boolean isIn = false;
                for(int j=0; j<domainList.size(); j++){
                    if(domainList.get(j).equals(domain)){
                        score.set(j, score.get(j)+1);
                        isIn = true;
                        break;
                    }
                }
                if(!isIn){
                    domainList.add(domain);
                    score.add(1);
                }
            }catch(MalformedURLException ex){
                //do exception handling here
            }

        }
        ArrayList<String> classedList = classDomain(domainList, score);
        return classedList;
    }

    private ArrayList<String> classDomain(ArrayList<String> listD, ArrayList<Integer> score){
        ArrayList<String> classed = new ArrayList<String>();
        boolean end = false;
        while(!end){
            end = true;
            for(int i=1; i<score.size(); i++){
                if(score.get(i)>score.get(i-1)){
                    end = false;
                    int tmp = score.get(i);
                    String tmpS = listD.get(i);
                    score.set(i, score.get(i-1));
                    listD.set(i, listD.get(i-1));
                    score.set(i-1, tmp);
                    listD.set(i-1, tmpS);
                }
            }
        }
        // for(int i=1; i<score.size(); i++){
        //     System.out.println(score.get(i));
        // }
        return listD;
    }
}
