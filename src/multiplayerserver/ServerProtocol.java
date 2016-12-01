/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplayerserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Mitchell
 */
class ServerProtocol implements Runnable {

    private final Socket clientConnection;
    private final ObjectInputStream inFromClient;
    private final ObjectOutputStream outToClient;
    private final EventQueue q;
    
    public ServerProtocol(Socket socket, EventQueue q) throws IOException {
        clientConnection = socket;
        this.q = q;
        inFromClient = new ObjectInputStream(clientConnection.getInputStream());
        outToClient = new ObjectOutputStream(clientConnection.getOutputStream());
    }

    @Override
    public void run() {
        try {
            Object in;
            
            in = inFromClient.readObject();
            System.out.println("In from client: " + in.toString());
            
            inFromClient.close();
            outToClient.close();
            clientConnection.close();
            System.out.println("Client disconnected.");
        } catch (IOException ex) {
            System.out.println("Unexpected Exception: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found: " + ex.getMessage());
        }
    }
    
}
