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
 * This class implements a TCP/IP server for hosting multiplayer games for the
 * Greatest Game Ever project.
 * @author Mitchell
 */
public class MultiplayerServer {

    private static final int port = 8080;
    private static final EventQueue q = new EventQueue();
    private static ServerProtocol p1;
    private static ServerProtocol p2;
    private static Player player1 = null;
    private static Player player2 = null;
    private static Lobby mpLobby;

    private static ServerSocket serverSocket;
    
    /**
     * Creates a lobby consisting of two players. Waits for both players to 
     * connect before starting a multiplayer game.
     * @todo handle player canceling matchmaking
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        int lobbyCtr = 0;
        serverSocket = new ServerSocket(port);
        
        while(true) {
            mpLobby = new Lobby();
            waitForPlayer1();
            waitForPlayer2();
            startGame();
            lobbyCtr++;
            System.out.println("Lobby " + lobbyCtr + " created!");
            resetPlayers();
        }
    }
    
    /**
     * Creates a new socket connection for the client.
     */
    private static ServerProtocol connectPlayer(Lobby l) throws IOException {
            System.out.println("Waiting for new connection...");
            Socket socket = serverSocket.accept();
            ServerProtocol sp = new ServerProtocol(socket, q);
            new Thread(sp).start();
            sp.setLobby(l);
            System.out.println("New player connected.");
        return sp;
    }
    
    /**
     * Resets the player objects for new player connections.
     */
    private static void resetPlayers() {
        player1 = null;
        player2 = null;
        p1 = null;
        p2 = null;
    }
    
    /**
     * Starts the multiplayer game.
     */
    private static void startGame() throws IOException {
        p1.sendBoard();
        p2.sendBoard();
    }
    
    /**
     * Implements the logic of connecting Player 1 to the server.
     * @throws IOException 
     */
    private static void waitForPlayer1() throws IOException {
        player1 = new Player();
        p1 = connectPlayer(mpLobby);
        p1.setPlayer(player1);
        mpLobby.setPlayer1(player1);
        System.out.println("Player 1 connected!");
    }
    
    /**
     * Implements the logic of connecting Player 2 to the server.
     * @throws IOException 
     */
    private static void waitForPlayer2() throws IOException {
        player2 = new Player();
        p2 = connectPlayer(mpLobby);
        p2.setPlayer(player2);
        p2.setOpponent(player1);
        p1.setOpponent(player2);
        mpLobby.setPlayer2(player2);
        System.out.println("Player 2 connected!");
    }
 
}
