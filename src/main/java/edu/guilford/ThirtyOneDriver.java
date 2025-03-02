package edu.guilford;

import java.util.*;

public class ThirtyOneDriver {
    private static final int NUM_PLAYERS = 2; // Change this to change the number of players
    private static final int INITIAL_CARDS = 3;
    private static final int KNOCK_SCORE = 24; // A player will knock if their hand is at least this score

    private static void turn(Player player, Stack<Card> discard, Queue<Card> stockpile, Random random) {
        /* Old basic code for a turn
        // The player puts the first card in their hand into the discard pile
        Card discardCard = player.getHand().getCard(0);
        player.getHand().removeCard(discardCard);
        discard.push(discardCard);

        // Then draws a card from the stockpile
        if (!stockpile.isEmpty()) {
            Card drawnCard = stockpile.poll();
            player.getHand().addCard(drawnCard);
        } */

        // First determine which suit the player keeps (highest score)
        Card.Suit targetSuit = player.getHand().getHighestSuit();

        // Choose which card to remove, first choosing the first one not of the target suit
        Card discardCard = null;
        for (Card card : player.getHand().getHand()) { // Get Hand from player then get Card array from Hand
            if (card.getSuit() != targetSuit) {
                discardCard = card;
                break;
            }
        }
        // If all cards are the same suit and the knock score is not met, discard the lowest value card
        if (discardCard == null) {
            discardCard = player.getHand().getCard(0); // Placeholder as null can't be compared to
        }
        for (Card card : player.getHand().getHand()) {
            if (card.compareTo(discardCard) < 0) { // compareTo() will return negative if card rank is lower
                discardCard = card;
            }
        }

        // Remove the card from the hand and randomly either add it to the top of discard or bottom of stockpile
        player.getHand().removeCard(discardCard);
        if (random.nextBoolean()) {
            discard.push(discardCard); // Add to top of discard pile
        }
        else {
            stockpile.add(discardCard); // Add to bottom of stockpile
        }

        // If the top card of the discard pile is of the target suit, take it. Otherwise, draw from stockpile.
        Card drawnCard;
        if (!discard.isEmpty() && discard.peek().getSuit() == targetSuit) {
            drawnCard = discard.pop();
        }
        else {
            drawnCard = stockpile.poll();

            // Refill the stockpile with the discard if it is empty, leaving one card
            if (drawnCard == null) {
                while (true) {
                    Card temp = discard.pop();
                    if (discard.peek() == null) { // If taking a card makes discard empty, restore it and stop
                        discard.push(temp);
                        drawnCard = stockpile.poll();
                        break;
                    }
                    stockpile.add(temp);
                }
            }
        }

        // Add the drawn card to the player's deck
        player.getHand().addCard(drawnCard);
    }

    private static void takeLives(Player players) {
        
    }

    public static void main(String[] args) {
        Random random = new Random();

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
            turn(player, discard, stockpile, random);
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

                turn(player, discard, stockpile, random);
            }
        }

        // Once someone knocks, everyone else gets one more turn
        for (Player player : players) {
            if (player != knocker) {
                turn(player, discard, stockpile, random);
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