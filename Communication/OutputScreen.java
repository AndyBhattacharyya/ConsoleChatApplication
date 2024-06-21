package Communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class OutputScreen implements Runnable{
    private Socket sock;
    private BufferedReader sock_recv;
    private String message_recv;
    public OutputScreen(Socket sock_recv) throws Exception{
        this.sock = sock_recv;
        this.sock_recv  = new BufferedReader(new InputStreamReader(sock_recv.getInputStream()));;
        this.message_recv = "";
    }
    public void run() {
        boolean donerunning = false;
        while(!donerunning){
            try {
                //unpredictable since using buffer
                //Testing
                //if (!sock.isClosed()) {
                message_recv = sock_recv.readLine();
                if(message_recv==null){
                    throw new NullPointerException();
                }
                System.out.println(message_recv);
                //}

            } catch(IOException e){
                System.out.println("OutputScreen: IOException");
                donerunning = true;
            }
            catch(NullPointerException e){
                //Server shuts down
                System.out.println("OutputScreen: NullPointerException, message_rec: + "+message_recv);
                try{System.in.close();}catch(IOException f){}
                donerunning = true;
            }
        }
        System.out.println("Exiting Thread Output");
    }


}
