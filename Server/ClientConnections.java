package Server;

import Communication.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientConnections {
    public Socket client;
    public PrintWriter client_output;
    public BufferedReader client_input;
    public ClientConnections(Socket client) throws Exception{
        this.client = client;
        this.client_output = new PrintWriter(client.getOutputStream());
        this.client_input = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    private static ExecutorService executor = Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws Exception{
        Server server = new Server();
        Socket tmp;
        ServerSocket listener = new ServerSocket(25565);
        do{
            if(server.isClientsEmpty()){
                executor.submit(new Server());
                System.out.println("Server begins");
            }
            tmp=listener.accept();
            server.addClient(new ClientConnections(tmp));
        }while(!server.isClientsEmpty());
        executor.shutdown();
    }
}
