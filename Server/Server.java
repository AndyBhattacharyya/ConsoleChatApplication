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
    private static String buildMessage(String username, BufferedReader console_in) throws Exception{
        LocalTime myObj = LocalTime.now();
        String time = myObj.toString();
        time = time.substring(0,5);
        return "["+time+"~"+username+"] "+console_in.readLine();
    }
    public static void main(String[] args) throws Exception {
        //Server Side Application. Receive then Send Model
        String username = setUsername();
        ServerSocket listener = new ServerSocket(25565);
        boolean done_processing = false;
        while(!done_processing){
            Socket sock = listener.accept();
            //Sock Streams
            BufferedReader sock_recv = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            PrintWriter sock_send = new PrintWriter(sock.getOutputStream(),true);
            //Confirming Connection
            sock_send.println("Connection Initiated To 8080");
            System.out.println(sock_recv.readLine());
            //Console Streams
            BufferedReader console_in;
            PrintWriter console_out;
            //Messaging
            String message_send="";
            String message_recv="";
            //Listen then Send
            while(!message_recv.equalsIgnoreCase("stop")){
                //Recieving Message First
                message_recv = sock_recv.readLine();
                System.out.println(message_recv);
                //Sending Message Last
                message_send= buildMessage(username, new BufferedReader(new InputStreamReader(System.in)));
                sock_send.println(message_send);
                sock_send.println(message_send);
            }
            done_processing=true;
        }
    }
}