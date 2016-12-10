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

    private static final Character QUIT = 'Q';
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
        
    }

    public void sendBoard() throws IOException {
        outToClient.writeObject(lobby.getPlayerBoard(player));
        System.out.println("Sent board to players.");
    }
    
    
    /**
     * Sets the lobby for this player's connection.
     * @param lobby 
     */
    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    /**
     * Gets the player for this server protocol.
     * @return 
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the player for this server protocol.
     * @param player 
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets the opponent for this server protocol.
     * @return 
     */
    public Player getOpponent() {
        return opponent;
    }

    /**
     * Sets the opponent for this server protocol.
     * @param opponent 
     */
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }
    
    /**
     * Disconnects the client from the server.
     * @throws IOException 
     */
    public void disconnect() throws IOException {
        inFromClient.close();
        outToClient.close();
        clientConnection.close();
        System.out.println("Client disconnected.");
    }
    
}
