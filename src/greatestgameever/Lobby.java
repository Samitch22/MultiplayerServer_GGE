/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greatestgameever;

import java.io.IOException;
import java.io.Serializable;
import model.Board;
import model.Player;

/**
 * A game lobby for players playing a multiplayer match.
 * @author Mitchell
 */
public class Lobby implements Serializable {
    
    private Player player1;
    private Player player2;
    private final Board board;
    
    /**
     * Default constructor that creates a board on construction.
     * @throws IOException
     */
    public Lobby() throws IOException {
        this.board = createBoard();
    }
    
    /**
     * Creates a new game lobby consisting of two players.
     * @param player1
     * @param player2
     * @throws java.io.IOException
     */
    public Lobby(Player player1, Player player2) throws IOException {
        this.player1 = player1;
        this.player2 = player2;
        this.board = createBoard();
    }
    
    /**
     * Gets player 1.
     * @return
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Gets player 2.
     * @return
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Sets Player 1.
     * @param player1
     */
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    /**
     * Sets Player 2.
     * @param player2
     */
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
    
    /**
     * Gets the board for the player specified.
     * @param player
     * @return
     * @throws java.io.IOException
     */
    public Board getPlayerBoard(Player player) throws IOException {
        Board playerBoard = new Board(this.board);
        playerBoard.setPlayer(player);
        return playerBoard;
    }
    
    /**
     * Sets this board to a new randomly created board.
     * @return 
     * @throws java.io.IOException 
     */
    private Board createBoard() throws IOException {
        Board b = new Board(new Player());
        return b;
    }
}
