package Server;
import Communication.Communication;

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
        ServerSocket listener = new ServerSocket(25565);
        Communication instance = new Communication(listener.accept());
        instance.start();
    }
}