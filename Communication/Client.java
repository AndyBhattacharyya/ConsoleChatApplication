package Communication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    private Socket sock;
    private String username;
    private final BufferedReader console_in= new BufferedReader(new InputStreamReader(System.in));
    private Thread output;
    private Thread send;
    private ThreadGroup communicate = new ThreadGroup("Socket Input and Output");
    private ExecutorService executor = Executors.newFixedThreadPool(3);
    private String setUsername(){
        Scanner inp = new Scanner(System.in);
        System.out.print("Enter your username: ");
        return inp.nextLine();
    }

    public Client(Socket sock) throws Exception{
        username = setUsername();
        output = new Thread(communicate, new OutputScreen(sock));
        send = new Thread(communicate, new SendMessage(sock,console_in,username));
    }
    public void start(){
        executor.submit(output);
        executor.submit(send);
        executor.shutdown();
    }
}
