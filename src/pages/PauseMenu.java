package pages;

import file.HighscoreFile;
import restaurant.CurrentRestaurant;

import java.util.Scanner;

public class PauseMenu {

    private Scanner scanner;
    public PauseMenu(){
        scanner = new Scanner(System.in);

        String input;
        do {
            System.out.println("PauseMenu");
            System.out.println("Status");
            System.out.printf("Money: Rp. %d\n", CurrentRestaurant.getInstance().getCurrentRestaurant().getMoney());
            System.out.printf("Score: %d Points\n", CurrentRestaurant.getInstance().getCurrentRestaurant().getScore());
            System.out.printf("Size: %d seats\n", CurrentRestaurant.getInstance().getCurrentRestaurant().getChairCount());
            System.out.println("1. Continue Business");
            System.out.println("2. Upgrade Restaurant");
            System.out.println("3. Close Business");
            System.out.println("Input [1..3]: ");
            input = scanner.nextLine();

            if(input.equals("1")) {
                CurrentRestaurant.getInstance().unpauseGame();
                return;
            }
            if(input.equals("2")) {
                new UpgradeRestaurant();
            }
            if(input.equals("3")) {
                HighscoreFile.getInstance().writeHighscore(CurrentRestaurant.getInstance().getCurrentRestaurant().getName(), CurrentRestaurant.getInstance().getCurrentRestaurant().getScore());
                CurrentRestaurant.getInstance().setCurrentRestaurant(null);
                return;
            }

        } while (true);


    }
}
