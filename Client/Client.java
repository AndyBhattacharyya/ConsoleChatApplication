package Client;

import Communication.Communication;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
public class Client {

    public static void main(String[] args) throws Exception{
        //Client Chat Application
        Communication instance = new Communication(new Socket("localhost", 25565));
        instance.start();

    }
}
