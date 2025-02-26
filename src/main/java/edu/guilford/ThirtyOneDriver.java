package edu.guilford;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class ThirtyOneDriver {
    private static final int NUM_PLAYERS = 2;
    private static final int INITIAL_CARDS = 3;
    private static final int KNOCK_SCORE = 20;

    public static void main(String[] args) {
        // Create a new game instance
        Game game = new Game();

        // Initialize the players
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < NUM_PLAYERS; i++) {
            players.add(new Player());
        }
        game.setPlayers(players);

        // Initialize and shuffle the deck
        Deck deck = game.getDeck();
        deck.shuffle();

        // Initialize the discard pile and stockpile, add deck to stockpile
        Stack<Card> discard = game.getDiscard();
        Queue<Card> stockpile = game.getStockpile();
        for (int i = 0; i < deck.size(); i++) {
            stockpile.add(deck.getDeck().get(i));
        }

        // Deal 3 cards to each player
        for (Player player : players) {
            for (int i = 0; i < INITIAL_CARDS; i++) {
                player.getHand().addCard(deck.deal());
            }
        }

        // Print scores of initial hands
        for (int i = 0; i < players.size(); i++) {
            int score = players.get(i).getHand().getTotalValue();
            Hand hand = players.get(i).getHand();
            System.out.println("Player " + (i + 1) + " hand: " + hand);
            System.out.println("Player " + (i + 1) + " hand score: " + score + "\n");
        }

        // Play the first round, no player can knock
        for (Player player : players) {
            // The player puts the first card in their hand into the discard pile
            Card discardCard = player.getHand().getCard(0);
            player.getHand().removeCard(discardCard);
            discard.push(discardCard);

            // Then draws a card from the stockpile
            if (!stockpile.isEmpty()) {
                Card drawnCard = stockpile.poll();
                player.getHand().addCard(drawnCard);
            }
        }

        // Continue the game until a player knocks
        boolean knock = false;
        Player knocker = null;
        while (!knock) {
            for (Player player : players) {
                // First check if a player should knock
                if (player.getHand().getTotalValue() >= KNOCK_SCORE) {
                    knock = true;
                    knocker = player;
                    break;
                }

                // Standard game loop: discard and draw
                Card discardCard = player.getHand().getCard(0);
                player.getHand().removeCard(discardCard);
                discard.push(discardCard);

                // Then draws a card from the stockpile
                if (!stockpile.isEmpty()) {
                    Card drawnCard = stockpile.poll();
                    player.getHand().addCard(drawnCard);
                }
            }
        }

        // Once someone knocks, everyone else gets one more turn
        for (Player player : players) {
            if (player != knocker) {
                Card discardCard = player.getHand().getCard(0);
                player.getHand().removeCard(discardCard);
                discard.push(discardCard);

                // Then draws a card from the stockpile
                if (!stockpile.isEmpty()) {
                    Card drawnCard = stockpile.poll();
                    player.getHand().addCard(drawnCard);
                }
            }
        }
        
        // Print final scores
        for (int i = 0; i < players.size(); i++) {
            int score = players.get(i).getHand().getTotalValue();
            Hand hand = players.get(i).getHand();
            System.out.println("Player " + (i + 1) + " hand: " + hand);
            System.out.println("Player " + (i + 1) + " hand score: " + score + "\n");
        }
    }
}