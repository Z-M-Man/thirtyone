package edu.guilford;

import java.util.*;

public class Game {
    private List<Player> players;
    private Deck deck;
    private Stack<Card> discard;
    private Queue<Card> stockpile;

    public Game() {
        this.players = new ArrayList<>();
        this.deck = new Deck();
        this.discard = new Stack<>();
        this.stockpile = new LinkedList<>();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Stack<Card> getDiscard() {
        return discard;
    }

    public void setDiscard(Stack<Card> discard) {
        this.discard = discard;
    }

    public Queue<Card> getStockpile() {
        return stockpile;
    }

    public void setStockpile(Queue<Card> stockpile) {
        this.stockpile = stockpile;
    }

    
}
