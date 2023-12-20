import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CardGames {

    private ArrayList<Card> Cards = new ArrayList<Card>();
    private int[] suits_index = new int[52];
    private int[] ranks_index = new int[52];
    private ArrayList<Player> players = new ArrayList<Player>();
    private HashMap<Player, ArrayList<Card>> cardsPlayerMap = new HashMap<Player, ArrayList<Card>>();

    public void GetPlayer(){

        if (players.size() != 0)
        {
            players.clear();
        }
        boolean incorrectNum = true;
        while (incorrectNum) {

            System.out.println("Please Enter No of Player (2 - 4)");
            Scanner inputNo = new Scanner(System.in);
            int noPlayer = inputNo.nextInt();

            if (noPlayer < 2 || noPlayer > 4) {
                System.out.println("Incorrect Number of Player");
                System.out.println();
                incorrectNum = true;
            }

            else {
                for (int i = 0; i < noPlayer; i++) {

                    System.out.println("Please Enter No " + (i + 1) + " player's name");
                    Scanner inputName = new Scanner(System.in);
                    String name = inputName.nextLine();
                    players.add(new Player(name,0));
                }
                for (int i = 0; i < players.size(); i++) {
                    System.out.println("Welcome " + players.get(i).getName());
                }
                incorrectNum = false;
            }
        }
    }

    public void ShuffleCard(){

        for(int count = 1; count <= 52; count++){
            int indexA = (int)(Math.random() * 52);
            int indexB = (int)(Math.random() * 52);
            Card temp = new Card("",0,"",0);
            temp = Cards.get(indexA);
            Cards.set(indexA,Cards.get(indexB));
            Cards.set(indexB,temp);
        }
    }

    public void SplitCards(ArrayList<Player> players) {

        if (cardsPlayerMap.size() != 0){
            cardsPlayerMap.clear();
        }

        int m = 0;
        for (Player pl : players)
        {
            ArrayList<Card> cds = new ArrayList<Card>();
            int cardLimit = m + 7;
            for (int i = m; i < cardLimit; i++) {
                cds.add(Cards.get(i));
            }
            m = cardLimit;
            cardsPlayerMap.put(pl, cds);
        }
    }

    public void DisplayCard() {

        int playernum = 0;
        for (Player pl : players)
        {
            System.out.print(players.get(playernum).getName() + "'s Hand Cards [ ");
            for (int i = 0; i < 7; i++) {
                System.out.print(cardsPlayerMap.get(pl).get(i) + " ");
            }
            System.out.println("]");
            playernum++;
        }
    }

    public void PlayGame(CardGames CG) {

        CG.GetPlayer();
        System.out.println();
        System.out.println();
        System.out.println("-------------------- Display Cards ----------------------");
        CG.showCards();
        
        System.out.println();
        System.out.println("-------------------- Start Game ----------------------");
        CG.ShuffleCard();
        CG.SplitCards(players);
        CG.DisplayCard();

        for (int cardleft = 7; cardleft > 0; cardleft--) {
            ArrayList<Card> compare = new ArrayList<>();
            int Noplayer = 0;
            for (Player pl: players) {
            	System.out.println();
                System.out.println("-------------------- " + players.get(Noplayer).getName() + "'s turns ----------------------");
                Scanner input = new Scanner(System.in);
                System.out.println("Player " + players.get(Noplayer).getName() + " please select an option");
                System.out.println("1. Continue Game");
                System.out.println("2. Exit Game");
                System.out.print("Enter your option: ");

                int option = input.nextInt();
                if (option == 1) {
                    CG.ChooseCard(Noplayer, pl, compare);
                } else if (option == 2) {
                	System.out.println();
                	CG.displayScores();
                    return;
                }

                Noplayer++;
            }
            if(cardleft == 0) {
                CG.displayScores();
            } else {
            	System.out.println();
                CG.CompareCards(compare);
                System.out.println();
                CG.displayScores();
            }
        }
    }

    public void ChooseCard(int Noplayer, Player pl,  ArrayList<Card> compare) {
    	
        System.out.print(players.get(Noplayer).getName() + "'s Hand Cards [ ");
        for (int i = 0; i < cardsPlayerMap.get(pl).size(); i++) {
            System.out.print(cardsPlayerMap.get(pl).get(i) + " ");
        }
        System.out.println("]");

        System.out.print("Player " + players.get(Noplayer).getName() + " please select a card (1 to " + cardsPlayerMap.get(pl).size() + "): ");
        Scanner input = new Scanner(System.in);
        int selected = input.nextInt() - 1;
        System.out.print(players.get(Noplayer).getName() + "'s selected card is: [ " + cardsPlayerMap.get(pl).get(selected) + " ]");
        System.out.println(" Ranks:" + cardsPlayerMap.get(pl).get(selected).getRanks_index() + " Suits:" +cardsPlayerMap.get(pl).get(selected).getSuits_index());
        compare.add(cardsPlayerMap.get(pl).get(selected));
        cardsPlayerMap.get(pl).remove(selected);
    }
    
    public void CompareCards(ArrayList<Card> c) {
        int largestIndex = 0;
        for (int i = 0; i < c.size() - 1; i++) {
            int j = i + 1;

            if (c.get(largestIndex).getRanks_index() == c.get(j).getRanks_index()) {
                if (c.get(j).getSuits_index() > c.get(largestIndex).getSuits_index()) {
                    largestIndex = j;
                } else {
                    largestIndex = largestIndex;
                }
            } else {
                if (c.get(j).getRanks_index() > c.get(largestIndex).getRanks_index()) {
                    largestIndex = j;
                } else {
                    largestIndex = largestIndex;
                }

            }

        }

        System.out.println("------------------------------------------");
        System.out.println("------------- Compare Card ---------------");
        System.out.println("------------------------------------------");
        int point = players.get(largestIndex).getPoint();
        players.get(largestIndex).setPoint(point + 1);
        int real = largestIndex + 1;
        System.out.println("Player " + real + " win with [" + c.get(largestIndex).toString() + "] ");

    }

    public void displayScores() {
    	
        for (int i = 0; i < players.size(); i++) {
            System.out.print(players.get(i).getName() + "'s Scores is = ");
            System.out.println( players.get(i).getPoint());
        }
    }
    
    public void displayWinner(){

        int largest = 0;
        int samenum = 0;
        for(int i = 0; i < players.size()-1;i++)
        {
            int j = i+1;
            if(players.get(largest).getPoint() == players.get(j).getPoint()) {
                largest = largest;
                samenum  = 1;

            }
            else{
                if(players.get(largest).getPoint() < players.get(j).getPoint()) {
                    largest = j;
                    samenum = 0;
                }else{
                    largest = largest;
                    samenum = 0;
                }
            }
        }
        
        System.out.println();
        if (samenum == 0) {
        	System.out.println("-------------------------------------------");
	        System.out.println("------------ The Winner is ----------------");
	        System.out.println("-------------- " + players.get(largest).getName() + " -------------------");
	        System.out.println("-------------------------------------------");
        } else {
        	System.out.println("---------------------------------------");
            System.out.println("------------ No Winner ----------------");
            System.out.println("---------------------------------------");
        }
        System.out.println();
    }
    
    public CardGames() {

        int index = 0;
        String[] suits = {"Clubs-","Diamond-","Hearts-","Spades-" };
        String[] ranks = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
        for(int i=0;i<4;i++){
            for(int j=0;j<13;j++){
                suits_index[index] = i+1;
                ranks_index[index] = j+1;
                Cards.add(new Card(suits[i],suits_index[index],ranks[j],ranks_index[index]));
                index++;
            }
        }
    }

    public void showCards() {
        for (int i = 0; i < Cards.size(); i++) {
            System.out.println(i + 1 + " " + Cards.get(i).toString());
        }
    }

}