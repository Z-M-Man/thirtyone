package edu.guilford;

public class Player {
    private int lives;
    private Hand hand;

    private final static int LIVES = 3;

    public Player() {
        this.lives = LIVES;
        this.hand = new Hand();
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}
