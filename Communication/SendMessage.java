package Communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalTime;

public class SendMessage implements Runnable {

    private Socket sock;
    private PrintWriter sock_send;
    private String message_send;
    private BufferedReader console_in;
    private String username;
    private boolean ClientQuit;


    private String buildMessage(String username, BufferedReader console_in) throws IOException{
        LocalTime myObj = LocalTime.now();
        String time = myObj.toString();
        time = time.substring(0,5);
        String message = console_in.readLine();
        if(sock.isClosed()){
            throw new IOException();
        }
        else{
            if(message.equalsIgnoreCase("stop")){
                ClientQuit = true;
                message = username+" has left";
                return message;
            }
            return "["+time+"~"+username+"] "+message;
        }
    }

    public SendMessage(Socket sock_send, BufferedReader console_in, String username) throws Exception{
        this.sock=sock_send;
        this.sock_send =  new PrintWriter(sock_send.getOutputStream(),true);
        this.message_send = username+" has joined";
        this.console_in = console_in;
        this.username = username;
        this.ClientQuit=false;
        this.sock_send.println(message_send);

    }
    public void run()   {
        boolean donerunning = false;
        while(!donerunning){
            try {
                message_send=buildMessage(username,console_in);
                sock_send.println(message_send);
                if(ClientQuit){
                    donerunning = true;
                    sock.close();
                }
            } catch(IOException e){
                System.out.println("SendMessage: IOException");
                donerunning = true;
            }
            catch(NullPointerException e){
                System.out.println("SendMessage: NullPointerException");
                donerunning = true;
            }

        }
        System.out.println("Exiting " + username +" thread SendMessage");
    }
}
