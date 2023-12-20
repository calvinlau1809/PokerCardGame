import java.util.Scanner;

public class GameDemo {

    public static void main(String[] args) {
        CardGames CG = new CardGames();

        int start = 1;
        while(start == 1){
            System.out.println("-------------------- Card Game ----------------------\nPlayer Options");
            System.out.println("1. Start Game \n2. Exit Game");
            System.out.print("Your option: ");
            Scanner input = new Scanner(System.in);
            start = input.nextInt();

            if(start == 1) {
                CG.PlayGame(CG);
                CG.displayWinner();
            }
            else if(start == 2) {
                System.out.println("Exit Game");
                break;
            }
            else {
                System.out.println("Please Try Again");
                start = 1;
            }
        }
        System.out.println("Thank You For Playing Our Game");
    }
}

