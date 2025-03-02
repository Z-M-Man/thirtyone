package edu.guilford;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<Card>();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void removeCard(Card card) {
        hand.remove(card);
    }

    public void reset() {
        hand.clear();
    }

    public int size() {
        return hand.size();
    }

    public Card getCard(int index) {
        return hand.get(index);
    }

    // Calculate the value of the hand
    public int getTotalValue() {
        int maxValue = 0;
        int[] values = new int[Card.Suit.values().length];
        for (Card.Suit suit : Card.Suit.values()) {
            values[suit.ordinal()] = 0;
            for (Card card : hand) {
                if (card.getSuit() == suit) {
                    // add the value of the card to the value of the suit
                    switch (card.getRank()) {
                        case ACE:
                            values[suit.ordinal()] += 11;
                            break;
                        case TWO:
                            values[suit.ordinal()] += 2;
                            break;
                        case THREE:
                            values[suit.ordinal()] += 3;
                            break;
                        case FOUR:
                            values[suit.ordinal()] += 4;
                            break;
                        case FIVE:
                            values[suit.ordinal()] += 5;
                            break;
                        case SIX:
                            values[suit.ordinal()] += 6;
                            break;
                        case SEVEN:
                            values[suit.ordinal()] += 7;
                            break;
                        case EIGHT:
                            values[suit.ordinal()] += 8;
                            break;
                        case NINE:
                            values[suit.ordinal()] += 9;
                            break;
                        case TEN:
                        case JACK:
                        case QUEEN:
                        case KING:
                            values[suit.ordinal()] += 10;
                            break;
                    }

                }
            }
        }
        maxValue = values[0];
        for (int value : values) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    // Find the suit that gives the highest hand score
    public Card.Suit getHighestSuit() {
        int maxValue = 0;
        Card.Suit highestSuit = null;
        int[] values = new int[Card.Suit.values().length]; // Array to store values for each suit
        for (Card.Suit suit : Card.Suit.values()) { // Loop through all the suits
            values[suit.ordinal()] = 0; // Initialize the suit's location in the array to 0
            for (Card card : hand) {
                if (card.getSuit() == suit) { // Sum up the values of every card matching the suit
                    switch (card.getRank()) {
                        case ACE:
                            values[suit.ordinal()] += 11;
                            break;
                        case TWO:
                            values[suit.ordinal()] += 2;
                            break;
                        case THREE:
                            values[suit.ordinal()] += 3;
                            break;
                        case FOUR:
                            values[suit.ordinal()] += 4;
                            break;
                        case FIVE:
                            values[suit.ordinal()] += 5;
                            break;
                        case SIX:
                            values[suit.ordinal()] += 6;
                            break;
                        case SEVEN:
                            values[suit.ordinal()] += 7;
                            break;
                        case EIGHT:
                            values[suit.ordinal()] += 8;
                            break;
                        case NINE:
                            values[suit.ordinal()] += 9;
                            break;
                        case TEN:
                        case JACK:
                        case QUEEN:
                        case KING:
                            values[suit.ordinal()] += 10;
                            break;
                    }
                }
            }
            if (values[suit.ordinal()] > maxValue) {
                maxValue = values[suit.ordinal()];
                highestSuit = suit;
            }
        }
        return highestSuit;
    }

    // Override toString method
    public String toString() {
        String handString = "";
        for (Card card : hand) {
            handString += card.toString() + "\n";
        }
        return handString;
    }

}
