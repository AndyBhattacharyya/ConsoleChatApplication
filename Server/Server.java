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

    public void addClient(ClientConnections client) {
        clients.add(client);
    }

    public boolean isClientsEmpty() {
        return clients.isEmpty();
    }

    private void OUTPUT(String message) {
        System.out.println("function entered " + message);
        for (ClientConnections client : clients) {
            System.out.println("OUTPUT: for loop entered");
            client.client_output.println(message);
            System.out.println("Finish Sending");
        }
    }

    public void run() {
        System.out.println("Thread Entered");
        while (true) {
            try {
                //System.out.println(clients.size());
                for (ClientConnections client : clients) {
                    System.out.println("for loop entered");
                    //Thread.sleep(1000);
                    if (client.availInput()) {
                        //System.out.println("if statement entered");
                        OUTPUT(client.client_input.readLine());
                    }
                    //System.out.println("for loop exited");
                    }
            } catch (InterruptedException e) {
                System.out.println("Interrupted Execption");
                break;
            } catch (IOException e) {
                System.out.println("IOException");
            } catch (Exception e) {
               System.out.println("availInput() error");
            }
            //System.out.println("Exiting Thread");
            //try{Thread.sleep(20);}catch(Exception e){}

        }
    }
}