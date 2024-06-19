package Server;
import Communication.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Server implements Runnable {

    private List<ClientConnections> clients = new ArrayList<ClientConnections>();
    public void addClient(ClientConnections client){
        clients.add(client);
    }
    public boolean isClientsEmpty(){
        return clients.isEmpty();
    }

    private void OUTPUT(String message){
        for(ClientConnections client:clients){
            client.client_output.println(message);
        }
    }
    public void run(){
        System.out.println("Thread Entered");
        while(true){
            try {
                for (ClientConnections client: clients) {
                    this.OUTPUT(client.client_input.readLine());
                }
            } catch(IOException e){
                System.out.println("error");
                    break;
            }
        }
        System.out.println("Exiting Thread");

    }
}