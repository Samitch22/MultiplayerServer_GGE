/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplayerserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Mitchell
 */
public class MultiplayerServer {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        final int port = 8080;
        final EventQueue q = new EventQueue();
        
        ServerSocket serverSocket = new ServerSocket(port);
        while(true) {
            System.out.println("Waiting for new connection...");
            Socket socket = serverSocket.accept();
            new Thread(new ServerProtocol(socket, q)).start();
            System.out.println("New client connection created.");
        }
    }
    
}
