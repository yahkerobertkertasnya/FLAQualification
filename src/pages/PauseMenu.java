package pages;

import restaurant.CurrentRestaurant;

import java.util.Scanner;

public class PauseMenu {

    private Scanner scanner;
    public PauseMenu(){
        scanner = new Scanner(System.in);
        System.out.println("PauseMenu");
        System.out.println("Status");
        System.out.printf("Money: Rp. %d\n", CurrentRestaurant.getInstance().getCurrentRestaurant().getMoney());
        System.out.printf("Score: %d Points\n", CurrentRestaurant.getInstance().getCurrentRestaurant().getScore());
        System.out.printf("Size: %d seats\n", CurrentRestaurant.getInstance().getCurrentRestaurant().getChairCount());
        System.out.println("1. Continue Business");
        System.out.println("2. Upgrade Restaurant");
        System.out.println("3. Close Business");

        String input;
        do {
            System.out.println("Input [1..3]: ");
            input = scanner.nextLine();

            System.out.println(CurrentRestaurant.getInstance().getCurrentRestaurant().getGameInput().isStopped());
            if(input.equals("1")) {
                CurrentRestaurant.getInstance().unpauseGame();
                System.out.println("hai");
                return;
            }
            if(input.equals("2")) {
                new UpgradeRestaurant();
            }

        } while (input.equals("3"));


    }
}
