package pages;

import restaurant.CurrentRestaurant;
import worker.cook.Cook;
import worker.server.Server;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class UpgradeRestaurant {
    private Scanner scanner;

    private void upgradeMainMenu(){
        System.out.println("Upgrade Menu");
        System.out.println("Status");
        System.out.printf("Money: Rp. %d\n", CurrentRestaurant.getInstance().getCurrentRestaurant().getMoney());
        System.out.printf("Score: %d Points\n", CurrentRestaurant.getInstance().getCurrentRestaurant().getScore());
        System.out.printf("Size: %d seats\n", CurrentRestaurant.getInstance().getCurrentRestaurant().getChairCount());
        System.out.println("Upgrade Restaurant");
        System.out.printf("1. Increase Restaurant's Seat <Rp. %d>\n", 100 * CurrentRestaurant.getInstance().getCurrentRestaurant().getChairCount());
        System.out.println("2. Hire New Employee");
        System.out.printf("3. Upgrade Waiter <Rp. %d>\n", 150);
        System.out.printf("4. Upgrade Cooker <Rp. %d>\n", 200);
        System.out.println("5. Back to Pause Menu");
        System.out.println("Input [1..5]: ");
    }
    private void upgradeHireMenu(){
        System.out.println("Upgrade Menu");
        System.out.println("Status");
        System.out.printf("Money: Rp. %d\n", CurrentRestaurant.getInstance().getCurrentRestaurant().getMoney());
        System.out.printf("Score: %d Points\n", CurrentRestaurant.getInstance().getCurrentRestaurant().getScore());
        System.out.printf("Size: %d seats\n", CurrentRestaurant.getInstance().getCurrentRestaurant().getChairCount());
        System.out.println("Hire New Employee");
        System.out.printf("1. Hire New Server <Rp. %d>\n", 150 * CurrentRestaurant.getInstance().getCurrentRestaurant().getServers().size());
        System.out.printf("2. Hire New Cook <Rp. %d>\n", 200 * CurrentRestaurant.getInstance().getCurrentRestaurant().getCooks().size());
        System.out.println("3. Back to Upgrade Menu");
        System.out.println("Input [1..3]: ");
    }
    public int upgradeServerMenu(){
        Vector<Server> servers = CurrentRestaurant.getInstance().getCurrentRestaurant().getServers();

        for(Server server : servers){
            System.out.printf("%d. %s %d\n", servers.indexOf(server) + 1, server.getName(), server.getSpeed());
        }
        System.out.println("Input employee number to upgrade [0 to exit]");

        return servers.size();
    }
    public int upgradeCookMenu(){
        Vector<Cook> cooks = CurrentRestaurant.getInstance().getCurrentRestaurant().getCooks();

        for(Cook cook : cooks){
            System.out.printf("%d. %s %d\n", cooks.indexOf(cook) + 1, cook.getName(), cook.getSpeed());
        }
        System.out.println("Input employee number to upgrade [0 to exit]");

        return cooks.size();
    }
    public UpgradeRestaurant(){
        scanner = new Scanner(System.in);
        String input3;
        String input;
        do {
            upgradeMainMenu();
            input = scanner.nextLine();

            if(input.equals("1")) {
                String message = CurrentRestaurant.getInstance().getCurrentRestaurant().upgradeChair();
                System.out.println(message);
            }
            else if(input.equals("2")) {

                String input2;
                do {
                    upgradeHireMenu();
                    input2 = scanner.nextLine();
                    if(input2.equals("1")) {
                        String message = CurrentRestaurant.getInstance().getCurrentRestaurant().hireServer();
                        System.out.println(message);
                    }
                    else if(input2.equals("2")) {
                        String message = CurrentRestaurant.getInstance().getCurrentRestaurant().hireCook();
                        System.out.println(message);
                    }
                    else if(input2.equals("3")) break;
                } while (true);
            }
            else if(input.equals("3")) {
                int serverCount = upgradeServerMenu();
                int input2;

                do {
                    System.out.printf("Input [1..%d]: ", serverCount);
                    input2 = scanner.nextInt();

                    if(input2 > 0 && input2 <= serverCount) {
                        String message = CurrentRestaurant.getInstance().getCurrentRestaurant().upgradeServer(input2);
                        System.out.println(message);

                    }
                    else if(input2 == 0) break;
                } while (true);
            }
            else if(input.equals("4")) {
                int cookCount = upgradeCookMenu();
                int input2;

                do {
                    System.out.printf("Input [1..%d]: ", cookCount);
                    input2 = scanner.nextInt();

                    if(input2 > 0 && input2 <= cookCount) {

                        do {
                            System.out.println("Upgrade 'speed' or 'skill'?");
                            input3 = scanner.nextLine();

                            if(input3.equals("speed") || input3.equals("skill")) {
                                String message = CurrentRestaurant.getInstance().getCurrentRestaurant().upgradeCook(input2, input3);
                                System.out.println(message);
                                break;
                            }
                        } while (true);
                    }
                    else if(input2 == 0) break;
                } while (true);
            }
            else if(input.equals("5")) break;
        } while (true);


    }
}
