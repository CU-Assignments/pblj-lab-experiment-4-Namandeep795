Experiment 4.2: Card Collection System

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Card {
    String value;
    String suit;

    Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return value.equals(card.value) && suit.equals(card.suit);
    }

    @Override
    public int hashCode() {
        return value.hashCode() + suit.hashCode();
    }
}

public class Main {
    Set<Card> cards = new HashSet<>();
    Map<String, List<Card>> suitMap = new HashMap<>();

    public void addCard(String value, String suit) {
        Card newCard = new Card(value, suit);
        if (cards.contains(newCard)) {
            System.out.println("Error: Card \"" + newCard + "\" already exists.");
        } else {
            cards.add(newCard);
            suitMap.computeIfAbsent(suit, k -> new ArrayList<>()).add(newCard);
            System.out.println("Card added: " + newCard);
        }
    }

    public void findCardsBySuit(String suit) {
        List<Card> cardsOfSuit = suitMap.get(suit);
        if (cardsOfSuit != null && !cardsOfSuit.isEmpty()) {
            System.out.println("Cards of suit " + suit + ":");
            for (Card card : cardsOfSuit) {
                System.out.println(card);
            }
        } else {
            System.out.println("No cards found for " + suit + ".");
        }
    }

    public void displayAllCards() {
        if (cards.isEmpty()) {
            System.out.println("No cards found.");
        } else {
            System.out.println("All Cards:");
            for (Card card : cards) {
                System.out.println(card);
            }
        }
    }

    public void removeCard(String value, String suit) {
        Card cardToRemove = new Card(value, suit);
        if (cards.contains(cardToRemove)) {
            cards.remove(cardToRemove);
            suitMap.get(suit).remove(cardToRemove);
            System.out.println("Card removed: " + cardToRemove);
        } else {
            System.out.println("Card not found: " + cardToRemove);
        }
    }

    public static void main(String[] args) {
        Main system = new Main();

        system.displayAllCards();

        system.addCard("Ace", "Spades");
        system.addCard("King", "Hearts");
        system.addCard("10", "Diamonds");
        system.addCard("5", "Clubs");

        system.findCardsBySuit("Hearts");
        system.findCardsBySuit("Diamonds");

        system.displayAllCards();

        system.addCard("King", "Hearts");

        system.removeCard("10", "Diamonds");
        system.displayAllCards();
    }
}



Objective:
Develop a Java program that collects and stores playing cards to help users find all the cards of a given symbol (suit).
The program should utilize the Collection interface (such as ArrayList, HashSet, or HashMap) to manage the card data efficiently.

Understanding the Problem Statement

1. Card Structure:
Each card consists of a symbol (suit) and a value (rank).

Example card representations:
Ace of Spades
King of Hearts
10 of Diamonds
5 of Clubs

2. Operations Required:
Add Cards → Store card details in a collection.
Find Cards by Symbol (Suit) → Retrieve all cards belonging to a specific suit (e.g., all "Hearts").
Display All Cards → Show all stored cards.

3. Collections Usage:
ArrayList: To store cards in an ordered manner.
HashSet: To prevent duplicate cards.
HashMap<String, List<Card>>: To organize cards based on suits for faster lookup.


Test Cases

Test Case 1: No Cards Initially
Input:
Display All Cards
Expected Output:
No cards found.

Test Case 2: Adding Cards
Input:
Add Card: Ace of Spades
Add Card: King of Hearts
Add Card: 10 of Diamonds
Add Card: 5 of Clubs
Expected Output:
Card added: Ace of Spades
Card added: King of Hearts
Card added: 10 of Diamonds
Card added: 5 of Clubs

Test Case 3: Finding Cards by Suit
Input:
Find All Cards of Suit: Hearts
Expected Output:
King of Hearts

Test Case 4: Searching Suit with No Cards
Input:
Find All Cards of Suit: Diamonds
(If no Diamonds were added)
Expected Output:
No cards found for Diamonds.

Test Case 5: Displaying All Cards
Input:
Display All Cards
Expected Output:
Ace of Spades
King of Hearts
10 of Diamonds
5 of Clubs

Test Case 6: Preventing Duplicate Cards
Input:
Add Card: King of Hearts
Expected Output:
Error: Card "King of Hearts" already exists.

Test Case 7: Removing a Card
Input:
Remove Card: 10 of Diamonds
Expected Output:
Card removed: 10 of Diamonds
