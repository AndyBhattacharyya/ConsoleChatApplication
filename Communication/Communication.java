package Communication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Scanner;

public class Communication implements Runnable {

    private Socket sock;
    private String username;
    private final BufferedReader console_in= new BufferedReader(new InputStreamReader(System.in));
    private Thread output;
    private Thread send;
    private ThreadGroup communicate = new ThreadGroup("Socket Input and Output");
    private String setUsername(){
        Scanner inp = new Scanner(System.in);
        System.out.print("Enter your username: ");
        return inp.nextLine();
    }

    public Communication(Socket sock) throws Exception{
        username = setUsername();
        output = new Thread(communicate, new OutputScreen(sock));
        send = new Thread(communicate, new SendMessage(sock,console_in,username));
    }
    public void start(){
        output.start();
        send.start();
    }
    public void run(){


    }


}
