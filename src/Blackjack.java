import java.util.Scanner;
import java.lang.Thread;

public class Blackjack {

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        String a = System.lineSeparator();
        System.out.println("Welcome to the last game of Blackjack you'll ever play!");
        sleep(2000);
        System.out.println("The rules are simple. Don't let your money reach zero.");
        sleep(2000);
        System.out.println("If it reaches zero, I claim your soul!");
        sleep(2000);
        System.out.println("What would you like to do?");
        System.out.println("1. Play" + a + "2. Read the rules (for beginners)");
        String select = scan.next();

        if (select.equals("1")) {

            // Create the playing deck
            Deck playingDeck = new Deck();
            playingDeck.createFullDeck();
            playingDeck.shuffleDeck();

            // Create hands for the player and the dealer - hands are created from methods
            // that are made in the deck class
            Deck playerHand = new Deck();
            Deck dealerHand = new Deck();

            // Set starting money
            System.out.println("Let's give you a chance. Set how much money you wish to start with.");
            int playerMoney = scan.nextInt();
            System.out.println("Your starting balance is $" + playerMoney);

            // Game loop while player still has money
            while (playerMoney > 0) {
                boolean endOfRound = false;
                System.out.println("Round start!");
                sleep(2000);
                System.out.println("Place your bets!");
                int playerBet = scan.nextInt();

                if (playerBet % 5 != 0) {
                    System.out.println("You are only allowed to bid in $5 increments.");
                    continue;
                }
                if (playerBet > playerMoney) {
                    System.out.println("Betting more than you have? Are you trying to speedrun your demise?");
                    continue;
                }

                // Both player and dealer draw their 2 starting cards
                playerHand.draw(playingDeck);
                playerHand.draw(playingDeck);
                dealerHand.draw(playingDeck);
                dealerHand.draw(playingDeck);

                // Player's turn
                while (true) {
                    System.out.println("Your hand is: ");
                    sleep(1000);
                    System.out.println(playerHand.toString());
                    sleep(1000);
                    System.out.println("Your hand's value is: " + playerHand.cardsValue());
                    sleep(1000);
                    System.out.println("The dealer's hand is: " + dealerHand.getCard(0).toString() + " and [Hidden]");
                    sleep(1000);
                    System.out.println("Would you like to double-down? Yes/No.");
                    String doubleDown = scan.next();

                    do {
                        if (doubleDown.equalsIgnoreCase("Yes")) {
                            if (playerBet * 2 > playerMoney) {
                                System.out.println(
                                        "How unfortunate. You're too poor to double-down. But the show must go on!");
                                sleep(2000);
                                System.out.println("Would you like to hit or stand?");
                                String hitOrStand = scan.next();

                                if (hitOrStand.equalsIgnoreCase("Hit")) {
                                    sleep(1000);
                                    playerHand.draw(playingDeck);
                                    System.out.println(
                                            "You drew: " + playerHand.getCard(playerHand.deckSize() - 1).toString());
                                    sleep(1000);
                                    System.out.println("Your hand's new value is: " + playerHand.cardsValue());
                                    sleep(1000);
                                    if (playerHand.cardsValue() > 21) {
                                        System.out.println("Looks like a bust!");
                                        playerMoney -= playerBet;
                                        endOfRound = true;
                                        break;
                                    }
                                } else if (hitOrStand.equalsIgnoreCase("Stand")) {
                                    break;
                                }

                            } else {
                                playerBet = playerBet * 2;
                                System.out.println("Someone is feeling gutsy heheh.");
                                sleep(1000);
                                System.out.println("You are now betting $" + playerBet);
                                sleep(1000);
                                System.out.println("Would you like to hit or stand?");
                                String hitOrStand = scan.next();

                                if (hitOrStand.equalsIgnoreCase("Hit")) {
                                    sleep(1000);
                                    playerHand.draw(playingDeck);
                                    System.out.println(
                                            "You drew: " + playerHand.getCard(playerHand.deckSize() - 1).toString());
                                    sleep(1000);
                                    System.out.println("Your hand's new value is: " + playerHand.cardsValue());
                                    sleep(1000);
                                    if (playerHand.cardsValue() > 21) {
                                        System.out.println("Looks like a bust!");
                                        playerMoney -= playerBet;
                                        endOfRound = true;
                                        break;
                                    }
                                } else if (hitOrStand.equalsIgnoreCase("Stand")) {
                                    break;
                                }
                                break;
                            }

                        } else if (doubleDown.equalsIgnoreCase("No")) {
                            System.out.println(
                                    "Chickening out are we? No matter. I'll bleed you dry regardless muahahaha!");
                            sleep(2000);
                            System.out.println("Would you like to hit or stand?");
                            String hitOrStand = scan.next();

                            if (hitOrStand.equalsIgnoreCase("Hit")) {
                                sleep(1000);
                                playerHand.draw(playingDeck);
                                System.out.println(
                                        "You drew: " + playerHand.getCard(playerHand.deckSize() - 1).toString());
                                sleep(1000);
                                System.out.println("Your hand's new value is: " + playerHand.cardsValue());
                                sleep(1000);
                                if (playerHand.cardsValue() > 21) {
                                    System.out.println("Looks like a bust!");
                                    playerMoney -= playerBet;
                                    endOfRound = true;
                                    break;
                                }
                            } else if (hitOrStand.equalsIgnoreCase("Stand")) {
                                break;
                            }
                        }
                    } while (doubleDown.equalsIgnoreCase("Yes"));
                    break;
                }

                // Dealer's turn
                while (endOfRound == false) {
                    System.out.println("Now it's time for the dealer to reveal their hand!");
                    sleep(3000);
                    System.out.println("The dealer's cards are: " + dealerHand.toString());
                    sleep(1000);
                    System.out.println("The dealer's value is: " + dealerHand.cardsValue());
                    sleep(1000);

                    while ((dealerHand.cardsValue() < 17)) {
                        dealerHand.draw(playingDeck);
                        System.out.println("Dealer drew: " + dealerHand.getCard(dealerHand.deckSize() - 1).toString());
                    }

                    if (playerHand.cardsValue() == dealerHand.cardsValue()) {
                        System.out.println("Not the outcome I was expecting.");
                        sleep(1000);
                        System.out.println("Push! It's a tie!");
                        endOfRound = true;
                    } else if (playerHand.cardsValue() == 21) {
                        System.out.println("HOW IS THAT POSSIBLE?!");
                        sleep(1000);
                        System.out.println("Player has blackjack! Player wins!");
                        playerMoney += playerBet;
                        endOfRound = true;
                    } else if (dealerHand.cardsValue() == 21) {
                        System.out.println("AHAHAHAHA LADY LUCK IS WITH ME THIS ROUND!");
                        sleep(1000);
                        System.out.println("Dealer has blackjack! Dealer wins!");
                        playerMoney -= playerBet;
                        endOfRound = true;
                    } else if ((dealerHand.cardsValue() >= 17) && (dealerHand.cardsValue() > playerHand.cardsValue())) {
                        System.out.println("Looks like it's my win AHAHAHAHAHA!");
                        sleep(1000);
                        System.out.println("Dealer wins!");
                        playerMoney -= playerBet;
                        endOfRound = true;
                    } else if ((dealerHand.cardsValue() >= 17) && (playerHand.cardsValue() > dealerHand.cardsValue())) {
                        System.out.println("You win this round. But you delay the inevitable...");
                        sleep(1000);
                        System.out.println("Player wins!");
                        playerMoney += playerBet;
                        endOfRound = true;
                    } else if (dealerHand.cardsValue() > 21) {
                        System.out.println("Trying to rig the game are we? I'll let it slide for now heheheheh...");
                        sleep(1000);
                        System.out.println("Dealer bust! Player wins!");
                        playerMoney += playerBet;
                        endOfRound = true;
                    }
                }

                playerHand.moveAllToDeck(playingDeck);
                dealerHand.moveAllToDeck(playingDeck);
                System.out.println("End of hand");
                sleep(1000);
                System.out.println("Your remaining amount is $" + playerMoney);
                sleep(1000);
            }

            if (playerMoney <= 0) {
                System.out.println("You lost all your money.");
                sleep(2000);
                System.out.println("NOW YOUR SOUL IS MINE FOR ALL ETERNITY AHAHAHAHAHA!!");
                sleep(2000);
                System.out.println("GAME OVER");
            }
        } else if (select.equals("2")) {
            System.out.println(
                    "Because I want you to put up a decent fight, I shall enlighten you to the rules of the game.");
            sleep(2000);
            System.out.println("The rules are as follows:");
            sleep(1000);
            System.out.println("01. The main goal in Blackjack is to beat the dealer's hand without going over 21.");
            System.out.println(
                    "02. You receive 2 cards at the start of each round, and you add up the values of these cards.");
            System.out.println(
                    "03. Cards 2-10 have face value. King, Queen, and Jack have a value of 10. Aces can be either 1 or 11 - your choice.");
            System.out.println(
                    "04. The dealer also receives 2 cards at the start of each round, but his second card will be hidden.");
            System.out.println("05. You then decide if you wish to double-down (double your initial bet).");
            System.out.println("06. Then you decide whether to hit or stand." + a +
                    "Hit means you want the dealer to deal you another card." + a +
                    "Stand means you do not want another card.");
            System.out.println(
                    "07. The dealer then reveals their hidden second card. From there, the game can go several ways.");
            System.out.println(
                    "08. If the value of the dealer's cards is below 17, they will continue to draw cards until the value is "
                            + a +
                            "greater than or equal to 17.");
            System.out.println(
                    "09. If the value of your cards exceeds 21, that is called a bust, and you lose the round.");
            System.out.println("10. If the value of the dealer's cards exceeds 21, they bust, and you win the round.");
            System.out.println(
                    "11. If the value of your cards equals 21, you win the round. The same logic applies"
                            + a +
                            "if the dealer's cards' value equal 21.");
            System.out.println(
                    "12. If both the dealer and player's card values are less than 21 and the dealer's card value is greater than 17:"
                            + a +
                            "If your card value is higher than the dealer's, you win." + a +
                            "If the dealer's card value is higher than you, you lose.");
            System.out.println(
                    "13. If both of you have the same value, that is called a push, which is a tie. No one wins or loses.");
            sleep(5000);
            System.out.println("Now that you know how to play, run it again and let's play...");
            sleep(2000);
        }
    }

    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Thread is interrupted");
            Thread.currentThread().interrupt();
        }
    }
}