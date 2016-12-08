/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplayerserver;

import greatestgameever.Lobby;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import model.Player;

/**
 * Multiplayer game connection protocol for the server.
 * @author Mitchell
 */
class ServerProtocol implements Runnable {

    private final Socket clientConnection;
    private final ObjectInputStream inFromClient;
    private final ObjectOutputStream outToClient;
    private final EventQueue q;
    private       Lobby lobby;
    private       Player player;
    private       Player opponent;
    
    /**
     * Constructs the server protocol taking in a socket and Event Queue. 
     * @param socket
     * @param q
     * @throws IOException 
     */
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
            //outToClient.writeObject(this.lobby.getPlayerBoard(null));
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

    /**
     * Sets the lobby for this player's connection.
     * @param lobby 
     */
    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }
    
    
}
