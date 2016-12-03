/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplayerserver;

import greatestgameever.Lobby;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import model.Player;

/**
 *
 * @author Mitchell
 */
public class MultiplayerServer {

    private static final int port = 8080;
    private static final EventQueue q = new EventQueue();

    private static ServerSocket serverSocket;
    
    /**
     * @todo handle player canceling matchmaking
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        Player player1 = null;
        Player player2 = null;
        Lobby mpLobby;
        int lobbyCtr = 0;
        
        serverSocket = new ServerSocket(port);
        while(true) {
            while (player1 == null) {
                player1 = connectPlayer();
                System.out.println("Player 1 connected!");
            }
            while (player2 == null) {
                player2 = connectPlayer();
                System.out.println("Player 2 connected!");
            }
            
            mpLobby = new Lobby(player1, player2);
            lobbyCtr++;
            System.out.println("Lobby " + lobbyCtr + " created!");
            player1 = null;
            player2 = null;
        }
    }
    
    /**
     * Creates a new socket connection for the client.
     */
    private static Player connectPlayer() {
        Player returnPlayer;
        try { 
            System.out.println("Waiting for new connection...");
            Socket socket = serverSocket.accept();
            new Thread(new ServerProtocol(socket, q)).start();
            returnPlayer = new Player();
            System.out.println("New player connected.");
            return returnPlayer;
        } catch (IOException ex) {
            System.out.println("Unexpected Exception: " + ex.getMessage());
        }
        
        return null;
    }
}
