import java.net.*;
import java.io.*;
import java.util.ArrayList;

class Main{
    public static void main(String[] args) throws Exception {
        Bot bot = new Bot();
        ArrayList<String> res = bot.urlReader("http://www.topito.com/", 0);
        for(int i=0; i<res.size(); i++){
            System.out.println(res.get(i));

            // System.out.println(domainList.get(i));
        }
        // for(int i=0; i<domainList.size(); i++){
        //     System.out.println(domainList.get(i));
        // }
        System.out.println(res.size() + " liens valides trouvés");
    }
}
