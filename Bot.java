import java.net.URL;
import java.io.IOException;

public class Bot {
  // private String url;
   public static void main(String[] args) {
     try{
       URL myURL = new URL("http://example.com/");

       System.out.println(myURL);
     }catch(Exception e){
       System.out.println(e);
     }

  }



}
