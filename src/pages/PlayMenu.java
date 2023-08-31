package pages;

import customer.Customer;
import helper.GameInput;
import restaurant.CurrentRestaurant;
import restaurant.Restaurant;
import worker.cook.Cook;
import worker.server.Server;

import java.util.Scanner;
import java.util.Vector;

public class PlayMenu {

    private Restaurant currRestaurant;
    private void printGameMenu(){
        Vector<Customer> customers = CurrentRestaurant.getInstance().getCurrentRestaurant().getCustomers();
        Vector<Server> servers = CurrentRestaurant.getInstance().getCurrentRestaurant().getServers();
        Vector<Cook> cooks = CurrentRestaurant.getInstance().getCurrentRestaurant().getCooks();
        System.out.printf("Restaurant '%s' is on Business!\n", this.currRestaurant.getName());
        System.out.println("Status");
        System.out.printf("Money: Rp. %d\n", this.currRestaurant.getMoney());
        System.out.printf("Score: %d Points\n", this.currRestaurant.getScore());
        System.out.printf("Size: %d seats\n", this.currRestaurant.getChairCount());
        System.out.println("============================================================================================================");
        System.out.println("  Customer                         Waiter                               Cook");
        System.out.println("============================================================================================================");
        int max = Math.max(Math.max(customers.size(), servers.size()), cooks.size());
        for(int i = 0; i < max; i++) {
            if(i < customers.size()) {
                Customer currCustomer = customers.get(i);
                System.out.printf("%s <%02d>, %-20s | ", currCustomer.getName(), currCustomer.getTolerance(), currCustomer.getPhase().getName());
            } else {
                System.out.printf("%-30s| ", "");
            }
            if(i < servers.size()) {
                Server currServer = servers.get(i);
                System.out.printf("%s, %-20s | ", currServer.getName(), currServer.getPhase().getName());
            } else {
                System.out.printf("%-25s| ", "");
            }
            if(i < cooks.size()) {
                Cook currCook = cooks.get(i);
                System.out.printf("%s, %-20s", currCook.getName(), currCook.getPhase().getName());
            }
            System.out.println();
        }
    }
    public PlayMenu(){
        Scanner scanner = new Scanner(System.in);
        String userInput;
        do {
            System.out.println("Input Restaurant Name [3..15]: ");
            userInput = scanner.nextLine();

        } while(userInput.length() < 3 || userInput.length() > 15);

        CurrentRestaurant.getInstance().setCurrentRestaurant(new Restaurant(userInput));

        this.currRestaurant = CurrentRestaurant.getInstance().getCurrentRestaurant();
        CurrentRestaurant.getInstance().pauseGame();
        do {
            try {
                Thread.sleep(1000);
            } catch (Exception ignored) {

            }
            if(CurrentRestaurant.getInstance().getCurrentRestaurant() != null && CurrentRestaurant.getInstance().getCurrentRestaurant().getCurrState() == CurrentRestaurant.getInstance().getCurrentRestaurant().getPausedState()) {
                continue;
            }
            if(CurrentRestaurant.getInstance().getCurrentRestaurant() == null) {
                return;
            }

            this.printGameMenu();
            CurrentRestaurant.getInstance().handleUpdate();
        } while (true);
//        String input;
//        do {
//            input = scanner.nextLine();
//            System.out.println(input);
//
//        } while (input.length() < 3 || input.length() > 15);

//        new PauseMenu();





    }
}
