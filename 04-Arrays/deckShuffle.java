/* Program: deckShuffle
 * Description: Consider a deck of cards of length n arranged in a row. 
 *              Proceeding from left to right, we pick a random card from 
 *              deck[i] through deck[n-1] (each card equally likely) and 
 *              exchange it with deck[i]. 
 */
public class deckShuffle {

    static final int N = 52;
    public static void main(String[] args) {
        
        String [] suits = {"C", "D", "H", "S"};
        String [] ranks = {"K", "Q", "J", "A", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        String [] deck = new String [N];

        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                deck[j + i*ranks.length] = suits[i] + ranks[j];
            }
        }
        printDeck(deck);

        for (int i = 0; i < N; i++) {
            int r = i + (int) (Math.random() * (N - i));
            if (r == i) continue;
            String temp = deck[i];
            deck[i] = deck[r];
            deck[r] = temp;
        }
        printDeck(deck);
    }

    public static void printDeck(String[] deck) {
        for (int i = 0; i < N; i++) {
            System.out.print(deck[i]);
            if ((i+1)%13 == 0) {
                System.out.println();
            } else {
                System.out.print("\t");
            }
        }
        System.err.println();
    }
}
