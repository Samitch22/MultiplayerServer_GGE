/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greatestgameever;

import model.Player;

/**
 * A game lobby for players playing a multiplayer match.
 * @author Mitchell
 */
public class Lobby {
    
    private final Player player1;
    private final Player player2;
    
    /**
     * Creates a new game lobby consisting of two players.
     * @param player1
     * @param player2
     */
    public Lobby(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
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
    
}
