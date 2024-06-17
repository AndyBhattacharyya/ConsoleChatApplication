package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
public class Client {

    private static String setUsername(){
        Scanner inp = new Scanner(System.in);
        System.out.print("Enter your username: ");
        return inp.nextLine();
    }

    public static void main(String[] args) throws Exception{
        String username = setUsername();
        //Client Chat Application
        Socket sock = new Socket("localhost",25565);
        boolean done_processing = false;
        //Setting up reference to standard input
        BufferedReader console_in= new BufferedReader(new InputStreamReader(System.in));
        //Listen and Sending Threads
        Thread output = new Thread(new Communication.OutputScreen(sock));
        output.start();
        Thread send = new Thread(new Communication.SendMessage(sock,console_in,username));
        send.start();

    }
}
