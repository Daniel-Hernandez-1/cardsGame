// Daniel Hernandez
// 11/10/15
// CSC 311
// Project 3

/* 
   Project 3: This program implements a simple card game with 2 players as required
   by the Project 3 PDF file and follows all directions and requirements. The card
   deck is created and the cards are plit randomly among the two players so that
   each has has 26 cards. A player is then randomly chosen to go first. Now the 
   game begins and after 10 rounds or if a player's hand is empty, the game ends.
   The player with the highest amount of cards wins.
*/

import java.util.*;

public class cardsGame
{
    public static void main(String[] args) // begin main method(String[] args) [5 lines]
    {
        cardsGame startGame = new cardsGame();
        
        startGame.playGame(); // play the game.
    } // end main
    
    public void playGame() // begin method playGame()
    {
        cardsGame game = new cardsGame(); // game variable used for non-player operations
        cardsGame player1 = new cardsGame(); // player 1
        cardsGame player2 = new cardsGame(); // player 2
        cardsGame table = new cardsGame(); // the table
        
        game.createDeck(); // create the deck of 52 cards
        
        // deals cards to each player
        player1.dealToPlayer();
        player2.dealToPlayer();
        
        int playerChosen = game.choosePlayer(); // choose which player goes first.
        
        
        // player 1 starts the game.
        if(playerChosen == 1) // begin if block (playuer 1 goes first)
        {
            System.out.println("Player 1 will start the game.");
            System.out.println();
            
            int rounds = 0; // counter for the rounds
            boolean playerHandEmpty = false; // used to indicate if a player runs out of cards
            int player1Count; // used to count player 1's cards at end of game
            int player2Count; // used to count player 2's cards at end of game
            
            // do-while loop until 10 rounds are played or a player runs out of cards
            do
            {
                // check if player 1 still has cards and if the other player hand is not empty
                if(player1.listEmpty() != true && playerHandEmpty == false)
                {
                    player1.removeFirst(); // remove card from player 1's hand.
                    
                    /* check for a suit match from player 1's card going to the table and the
                       top card currently on the table. 
                    */
                    if(game.playerTableSuitMatch() == true)
                    {
                        table.addFirst(suitCaptured, numberCaptured); // take that card and add to table.
                        
                        while(table.listEmpty() != true) // player 1 will take the cards on the table
                        {
                            table.removeFirst();
                            player1.addEnd(suitCaptured, numberCaptured);
                        }
                    }
                    else
                    {
                        table.addFirst(suitCaptured, numberCaptured); // no match, then put card on table only.
                    }
                }
                else
                {
                    playerHandEmpty = true; // if player 1 has no more cards in their hand.
                }
                
                // check if player 2 still has cards and that the other player doesnt have an empty hand
                if(player2.listEmpty() != true && playerHandEmpty == false)
                {   
                    player2.removeFirst(); // remove top card from player 2's hand/deck.
                    
                    /* checks to see if the suit of player 2's card going to the table
                       matches the current top card's suit on the table.
                    */
                    if(game.playerTableSuitMatch() == true)
                    {
                        table.addFirst(suitCaptured, numberCaptured); // put card on table
                        
                        // player 2 will take all cards on the table
                        while(table.listEmpty() != true)
                        {
                            table.removeFirst();
                            player2.addEnd(suitCaptured, numberCaptured);
                        }
                    }
                    else
                    {
                        table.addFirst(suitCaptured, numberCaptured); // when no match, just put the card on the table
                    }
                }
                else
                {
                    playerHandEmpty = true; // if player 2 has run out of cards, make this true.
                }
                
                /*
                The next few lines output the current round, player 1's hand, 
                player 2's hand, and the cards currently on the table.
                */
                System.out.println("-- Round " + (rounds + 1) + " --");
                System.out.println();
                
                System.out.println("Player 1's Hand:");
                player1.printList();
                
                System.out.println("Player 2' Hand:");
                player2.printList();
                
                System.out.println("Table:");
                table.printList();
                
                rounds++; // increment the rounds counter.
            } while(rounds < 10 && playerHandEmpty == false);
            
            player1Count = player1.countList(); // add up player 1's cards
            player2Count = player2.countList(); // add up player 2's cards
            
            /* The following if conditions print out the various ways a player has 
               won the game or if a tie has occured.
            */
            if((player2Count < player1Count) && player2.listEmpty() == false && player1Count != 52)
            {
                System.out.println("Player 1 has " + player1Count + " cards and Player 2 has " + player2Count + " cards.");
                System.out.println("Player 1 wins the game by having the most cards.");
            }
            
            if((player1Count < player2Count) && player1.listEmpty() == false && player2Count != 52)
            {
                System.out.println("Player 1 has " + player1Count + " cards and Player 2 has " + player2Count + " cards.");
                System.out.println("Player 2 wins the game by having the most cards.");
            }
            
            if((player2Count < player1Count) && player2.listEmpty() == true && player1Count != 52)
            {
                System.out.println("Player 1 has " + player1Count + " cards and Player 2 has " + player2Count + " cards.");
                System.out.println("Player 1 wins the game due to Player 2 running out of cards.");
            }
            
            if((player1Count < player2Count) && player1.listEmpty() == true && player2Count != 52)
            {
                System.out.println("Player 1 has " + player1Count + " cards and Player 2 has " + player2Count + " cards.");
                System.out.println("Player 2 wins the game due to Player 1 running out of cards.");
            }
            
            if(player1Count == player2Count)
            {
                System.out.println("Player 1 has " + player1Count + " cards and Player2 has " + player2Count + " cards");
                System.out.println("The game ends in a tie due to both players having the same amount of cards.");
            }
            
            if(player1Count == 52)
            {
                System.out.println("Player 1 has " + player1Count + " cards and Player 2 has " + player2Count + " cards.");
                System.out.println("Player 1 wins the game by having all 52 cards.");
            }
            
            if(player2Count == 52)
            {
                System.out.println("Player 1 has " + player1Count + " cards and Player 2 has " + player2Count + " cards.");
                System.out.println("Player 2 wins the game by having all 52 cards.");
            }
        } // end if block (player 1 goes first)
        
        /* Player 2 begins the game. Follow all the comments from the top as the code
           will perform the same exact thing as above, the only exception is that player 2
           is the one that goes first this time.
        */
        if(playerChosen == 2) // begin if block (player 2 goes first)
        {
            System.out.println("Player 2 will start the game.");
            System.out.println();
            
            int rounds = 0;
            boolean playerHandEmpty = false;
            int player1Count;
            int player2Count;
            
            do
            {
                if(player2.listEmpty() != true && playerHandEmpty == false)
                {   
                    player2.removeFirst();
                    
                    if(game.playerTableSuitMatch() == true)
                    {
                        table.addFirst(suitCaptured, numberCaptured);
                        
                        while(table.listEmpty() != true)
                        {
                            table.removeFirst();
                            player2.addEnd(suitCaptured, numberCaptured);
                        }
                    }
                    else
                    {
                        table.addFirst(suitCaptured, numberCaptured);
                    }
                }
                else
                {
                    playerHandEmpty = true;
                }
                
                if(player1.listEmpty() != true && playerHandEmpty == false)
                {
                    player1.removeFirst();
                    
                    if(game.playerTableSuitMatch() == true)
                    {
                        table.addFirst(suitCaptured, numberCaptured);
                        
                        while(table.listEmpty() != true)
                        {
                            table.removeFirst();
                            player1.addEnd(suitCaptured, numberCaptured);
                        }
                    }
                    else
                    {
                        table.addFirst(suitCaptured, numberCaptured);
                    }
                }
                else
                {
                    playerHandEmpty = true;
                }
                
                System.out.println("-- Round " + (rounds + 1) + " --");
                System.out.println();
                
                System.out.println("Player 1's Hand:");
                player1.printList();
                
                System.out.println("Player 2' Hand:");
                player2.printList();
                
                System.out.println("Table:");
                table.printList();
                
                rounds++;
            } while(rounds < 10 && playerHandEmpty == false);
            
            player1Count = player1.countList();
            player2Count = player2.countList();
            
            if((player2Count < player1Count) && player2.listEmpty() == false && player1Count != 52)
            {
                System.out.println("Player 1 has " + player1Count + " cards and Player 2 has " + player2Count + " cards.");
                System.out.println("Player 1 wins the game by having the most cards.");
            }
            
            if((player1Count < player2Count) && player1.listEmpty() == false && player2Count != 52)
            {
                System.out.println("Player 1 has " + player1Count + " cards and Player 2 has " + player2Count + " cards.");
                System.out.println("Player 2 wins the game by having the most cards.");
            }
            
            if((player2Count < player1Count) && player2.listEmpty() == true && player1Count != 52)
            {
                System.out.println("Player 1 has " + player1Count + " cards and Player 2 has " + player2Count + " cards.");
                System.out.println("Player 1 wins the game due to Player 2 running out of cards.");
            }
            
            if((player1Count < player2Count) && player1.listEmpty() == true && player2Count != 52)
            {
                System.out.println("Player 1 has " + player1Count + " cards and Player 2 has " + player2Count + " cards.");
                System.out.println("Player 2 wins the game due to Player 1 running out of cards.");
            }
            
            if(player1Count == player2Count)
            {
                System.out.println("Player 1 has " + player1Count + " cards and Player 2 has " + player2Count + " cards.");
                System.out.println("The game ends in a tie due to both players having the same amount of cards.");
            }
            
            if(player1Count == 52)
            {
                System.out.println("Player 1 has " + player1Count + " cards and Player 2 has " + player2Count + " cards.");
                System.out.println("Player 1 wins the game by having all 52 cards.");
            }
            
            if(player2Count == 52)
            {
                System.out.println("Player 1 has " + player1Count + " cards and Player 2 has " + player2Count + " cards.");
                System.out.println("Player 2 wins the game by having all 52 cards.");
            }
        } // end if block (player 2 goes first)
    } // end playGame
    
    // checks if the card being put on the table by the current player matches top card on the table
    public boolean playerTableSuitMatch() // begin playerTableSuitMatch() method
    {
        return cardsGame.suitCaptured.equals(cardsGame.nextSuit);
    } // end playerTableSuitMatch
    
    // selects which player goes first
    public int choosePlayer() // begin choosePlayer() method
    {
        Random RNGesus = new Random(); // random number generator
        
        int number = RNGesus.nextInt(10) + 1; // generate random number 1 to 10
        
        // if number is odd, player 1 goes first, if even player 2 goes first.
        if(number % 2 == 1)
        {
            return 1;
        }
        else
        {
            return 2;
        }
    } // end choosePlayer
    
    /* 
    method dealToPlayer takes the 52 cards and randomly splits them between 
    the 2 players. A random number generator is used to pick out the cards from
    the 2D array.
    */
    public void dealToPlayer() // begin dealToPlayer() method
    {   
        String suitChosen; // store the suit
        String numberChosen; // store the number
        int count = 0; // counter
        
        Random RNGesus = new Random(); // random number generator
        
        // do-while loop to randomly pick 26 cards for a player
        do
        {
            int num = RNGesus.nextInt(52); // num is the range of 2D array [0 to 51] (first card to the 52nd card)
            
            // check to see if the index of 2D array has already been chosen before
            // in other words, has that card already been picked and given to a player already.
            if(!(cardsGame.deck[0][num].equals("")))
            {
                suitChosen = cardsGame.deck[0][num]; // store the suit
                numberChosen = cardsGame.deck[1][num]; // store the number
                
                addFirst(suitChosen, numberChosen); // add to the player's hand/deck
                
                // sets the strings in the current element to "" to indicate that the card there
                // has been picked and removed from the deck (2D array)
                cardsGame.deck[0][num] = "";
                cardsGame.deck[1][num] = "";
                
                count++; // increment counter
            }
        } while(count < 26);
        
        cardsGame.nextSuit = ""; // used because we are dealing to players, no need for it to store a value yet.
        cardsGame.nextNumber = ""; // used because are dealing to players, no need for it to store a value yet
    } // end dealToPlayer
    
    /*
    This method creates the 52 card deck by using a 2D array that stores the suit in the top row
    and the number in the bottom row. The deck is always in the same order but cards become randomized
    when dealt to player in the dealToPlayer method above.
    */
    public void createDeck() //begin createDeck() method
    {
        // for loop is used to create the 52 cards.
        for(int i = 0; i < 52; i++)
        {
            // for first 13 cards, this if block executes
            if(i < 13)
            {
                cardsGame.deck[0][i] = "Clubs"; // create 13 cards with clubs suit.
                
                if(i == 0)
                {
                    cardsGame.deck[1][i] = "Ace"; // first card is an Ace
                }
                
                // cards 2 - 10 will contain the number 2 - 10
                if(i > 0 && i < 10)
                {
                    cardsGame.deck[1][i] = Integer.toString(i + 1);
                }
                
                // 11th card is a Jack
                if(i == 10)
                {
                    cardsGame.deck[1][i] = "Jack";
                }
                
                // 12th card is a Queen
                if(i == 11)
                {
                    cardsGame.deck[1][i] = "Queen";
                }
                
                // 13th card is a King
                if(i == 12)
                {
                    cardsGame.deck[1][i] = "King";
                }
            }
            
            // for next 13 cards, this if block executes
            if(i > 12 && i < 26)
            {
                cardsGame.deck[0][i] = "Diamonds"; // the suit is Diamonds
                
                if(i == 13)
                {
                    cardsGame.deck[1][i] = "Ace"; // first card is Ace
                }
                
                // creates cards with numbers 2 to 10
                if(i > 13 && i < 23)
                {
                    cardsGame.deck[1][i] = Integer.toString(i - 12);
                }
                
                // create Jack card
                if(i == 23)
                {
                    cardsGame.deck[1][i] = "Jack";
                }
                
                // create Queen card
                if(i == 24)
                {
                    cardsGame.deck[1][i] = "Queen";
                }
                
                // create King card
                if(i == 25)
                {
                    cardsGame.deck[1][i] = "King";
                }
            }
           
            // 3rd set of 13 cards, this if block executes
           if(i > 25 && i < 39)
           {
               cardsGame.deck[0][i] = "Hearts"; // suit is Heards
               
               if(i == 26)
               {
                   cardsGame.deck[1][i] = "Ace"; // first card is Ace
               }
               
               if(i > 26 && i < 36) // creates cards with numbers 2 to 10
               {
                   cardsGame.deck[1][i] = Integer.toString(i - 25);
               }
               
               if(i == 36) // creates Jack card
               {
                   cardsGame.deck[1][i] = "Jack";
               }
               
               if(i == 37) // creates Queen card
               {
                   cardsGame.deck[1][i] = "Queen";
               }
               
               if(i == 38) // creates King card
               {
                   cardsGame.deck[1][i] = "King";
               }
           }
           
           // 4th/final set of 13 cards, this if block executes.
           if(i > 38 && i < 52)
           {
               cardsGame.deck[0][i] = "Spades"; // suit is Spades
               
               if(i == 39) // first card number is Ace
               {
                   cardsGame.deck[1][i] = "Ace";
               }
               
               if(i > 39 && i < 49) // creates cards with numbers 2 to 10.
               {
                   cardsGame.deck[1][i] = Integer.toString(i - 38);
               }
               
               if(i == 49) // create Jack card
               {
                   cardsGame.deck[1][i] = "Jack";
               }
               
               if(i == 50) // create Queen card
               {
                   cardsGame.deck[1][i] = "Queen";
               }
               
               if(i == 51) // crete King card
               {
                   cardsGame.deck[1][i] = "King";
               }
           }
        }
    } // end createDeck
    
    /*
        The rest of the code implements a linked list.
    */
    private static class Node
    {
        public Node(String suit, String number)
        {
            cardSuit = suit;
            cardNumber = number;
        }
        
        String cardSuit;
        String cardNumber;
        Node link;
    }
    
    public cardsGame()
    {
        listHead = null;
    }
    
    public void addFirst(String suit, String number)
    {
        Node ptr = new Node(suit, number);
        
        cardsGame.nextSuit = suit;
        cardsGame.nextNumber = number;
        
        if(listHead == null)
        {
            listHead = ptr;
        }
        else
        {
            Node tptr = listHead;
            listHead = ptr;
            ptr.link = tptr;
        }
    }
    
    // used when adding cards from the table to the end of the player's deck/hand.
    public void addEnd(String suit, String number)
    {
        Node ptr = new Node(suit, number);
        
        cardsGame.nextSuit = "";
        cardsGame.nextNumber = "";
        
        if(listHead == null)
        {
            listHead = ptr;
        }
        else
        {
            Node tptr = listHead;
            
            while(tptr != null)
            {
                listTrace = tptr;
                tptr = tptr.link;
            }
            
            listTrace.link = ptr;
        }
    }
    
    public Object removeFirst()
    {
        if(listHead == null)
        {
            System.out.println("No card to remove.");
            return null;
        }
        else
        {
            Node ptr = listHead;
            listHead = ptr.link;
            ptr.link = ptr;
            
            cardsGame.suitCaptured = ptr.cardSuit;
            cardsGame.numberCaptured = ptr.cardNumber;
            
            return ptr;
        }
    }
    
    // checks if a player has run out of cards.
    public boolean listEmpty()
    {
        return listHead == null;
    }
    
    // used to print out the hand/deck of the players and the cards on the table
    public void printList()
    {
        Node ptr = listHead;
        
        while(ptr != null)
        {
            System.out.println(ptr.cardNumber + " of " + ptr.cardSuit);
            ptr = ptr.link;
        }
        
        System.out.println();
    }
    
    /*
    This is the only new method that is used to count how many cards
    a player has by going through the list and keeping a count that 
    increments until the end of the linked list is reached.
    */
    public int countList()
    {
        Node ptr = listHead;
        int count = 0;
        
        while(ptr != null)
        {
            count++;
            ptr = ptr.link;
        }
        
        return count;
    }
    
    // Global variables used in this program.
    private Node listHead;
    private Node listTrace;
    private static final String[][] deck = new String[52][52];
    public static String suitCaptured;
    public static String numberCaptured;
    public static String nextSuit;
    public static String nextNumber;
}