package Client;
import java.io.*;
import javax.net.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.Scanner;
public class Client {

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
    public static void main(String[] args) throws Exception{
        String username = setUsername();
        //Client Chat Application
        Socket sock = new Socket("localhost",25565);
        //Console Streams
        PrintWriter console_out;
        //Sock Streams
        BufferedReader sock_recv = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        PrintWriter sock_send = new PrintWriter(sock.getOutputStream(), true);
        //Confirming Connection
        System.out.println(sock_recv.readLine());
        sock_send.println(username+" Joined");
        //Messaging
        String message_send="";
        String message_recv="";
        //Console Streams
        BufferedReader console_in = new BufferedReader(new InputStreamReader(System.in));

        //Boolean to keep thread blocked
        boolean done_chat=false;
        while(!done_chat) {
            while (!message_recv.equalsIgnoreCase("stop")) {
                //Sending Message First
                message_send= buildMessage(username, console_in);
                sock_send.println(message_send);
                //Recieving Message Later
                message_recv = sock_recv.readLine();
                System.out.println(message_recv);
            }
            done_chat=true;
        }
        sock.close();

    }
}
