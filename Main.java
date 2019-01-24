import java.net.*;
import java.io.*;
import java.util.ArrayList;

class Main{
    public static void main(String[] args) throws Exception {
        Bot bot = new Bot();
        ArrayList<String> res = bot.urlReader("http://www.topito.com/", 0);
        ArrayList<String> domainList = new ArrayList<String>();
        for(int i=0; i<res.size(); i++){
            // System.out.println(res.get(i));
            URL adr = new URL(res.get(i));
            String domain =  adr.getHost();
            String[] masterDom = domain.split("\\.");
            domain = masterDom[masterDom.length-2] + "." + masterDom[masterDom.length-1];
            // System.out.println(masterDom[masterDom.length-2] + "." + masterDom[masterDom.length-1]);
            boolean isIn = false;
            for(int j=0; j<domainList.size(); j++){
                if(domainList.get(j).equals(domain)){
                    isIn = true;
                    break;
                }
            }
            if(!isIn){
                domainList.add(domain);
            }
            // System.out.println(domainList.get(i));
        }
        for(int i=0; i<domainList.size(); i++){
            System.out.println(domainList.get(i));
        }
        System.out.println(res.size() + " liens valides trouvés");
    }
}
