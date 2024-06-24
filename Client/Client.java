package Client;

import Communication.OutputScreen;
import Communication.SendMessage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    private Socket sock;
    private String username;
    private final BufferedReader console_in= new BufferedReader(new InputStreamReader(System.in));
    private ExecutorService executor = Executors.newFixedThreadPool(2);

    private String setUsername(){
        Scanner inp = new Scanner(System.in);
        System.out.print("Enter your username: ");
        return inp.nextLine();
    }
    public Client(Socket sock) {
        username = setUsername();
        this.sock = sock;
    }
    public void start() throws Exception{
        executor.submit(new OutputScreen(sock));
        executor.submit(new SendMessage(sock,console_in,username));
        executor.shutdown();
    }
    public static void main(String[] args) throws Exception{
        //Starting the application
        new Client(new Socket("99.245.105.87",25565)).start();
    }
}
