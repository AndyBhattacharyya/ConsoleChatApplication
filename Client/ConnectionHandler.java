package Client;

import java.net.Socket;

public class ConnectionHandler {

    public static void main(String[] args) throws Exception{
        //Client Chat Application
        Communication.Client instance = new Communication.Client(new Socket("localhost", 25565));
        instance.start();



    }
}
