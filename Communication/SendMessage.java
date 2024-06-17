package Communication;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalTime;

public class SendMessage implements Runnable {
    private PrintWriter sock_send;
    private String message_send;
    private BufferedReader console_in;
    private String username;

    private String buildMessage(String username, BufferedReader console_in) throws Exception{
        LocalTime myObj = LocalTime.now();
        String time = myObj.toString();
        time = time.substring(0,5);
        String message = console_in.readLine();
        return message.equalsIgnoreCase("stop")?"stop":"["+time+"~"+username+"] "+message;
    }
    public SendMessage(Socket sock_send, BufferedReader console_in, String username) throws Exception{
        this.sock_send =  new PrintWriter(sock_send.getOutputStream(),true);
        this.message_send = "";
        this.console_in = console_in;
        this.username = username;

    }
    public void run() {
        boolean donerunning = false;
        while(!donerunning){
            try {
                message_send=buildMessage(username,console_in);
                sock_send.println(message_send);
            } catch(Exception e){
                donerunning = true;
            }
        }
    }
}
