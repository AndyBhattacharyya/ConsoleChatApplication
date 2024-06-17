package Server;
import java.io.*;
import javax.net.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.Scanner;
public class Server {
    private static String setUsername(){
        Scanner inp = new Scanner(System.in);
        System.out.print("Enter your username: ");
        return inp.nextLine();
    }
    public static void main(String[] args) throws Exception {
        //Server Side Application. Receive then Send Model
        String username = setUsername();
        ServerSocket listener = new ServerSocket(25565);
        BufferedReader console_in= new BufferedReader(new InputStreamReader(System.in));
        boolean done_processing = false;
        Socket sock = listener.accept();
        //Setting up reference to standard input
        //Listen and Sending Threads
        Thread output = new Thread(new Communication.OutputScreen(sock));
        output.start();
        Thread send = new Thread(new Communication.SendMessage(sock,console_in,username));
        send.start();

    }
}