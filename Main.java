import java.net.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception {
        Bot bot = new Bot();
        System.out.println(bot.urlReader("https://www.oracle.com"));
    }
}
