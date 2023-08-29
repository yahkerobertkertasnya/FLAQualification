package helper;

import customer.Customer;
import restaurant.CurrentRestaurant;
import worker.cook.Cook;
import worker.server.Server;

import java.util.Random;

public class NameGenerator {
    private static Random rng = new Random();
    public static String generateName() {

        boolean breakLoop = false;
        do {
            breakLoop = false;
            char first = (char) (rng.nextInt(26) + 'A');
            char second = (char) (rng.nextInt(26) + 'A');

            String name = String.format("%s%s", first, second);

            if(CurrentRestaurant.getInstance().getCurrentRestaurant() == null) return name;
            for(Customer currCustomer : CurrentRestaurant.getInstance().getCurrentRestaurant().getCustomers()) {
                if (currCustomer.getName().equals(name)) {
                    breakLoop = true;
                    break;
                }
            }
            if(breakLoop) continue;
            for(Server currServer : CurrentRestaurant.getInstance().getCurrentRestaurant().getServers()) {
                if (currServer.getName().equals(name)) {
                    breakLoop = true;
                    break;
                }
            }
            if(breakLoop) continue;
            for(Cook currCook : CurrentRestaurant.getInstance().getCurrentRestaurant().getCooks()) {
                if (currCook.getName().equals(name)) {
                    breakLoop = true;
                    break;
                }
            }
            if(breakLoop) continue;

            return name;
        } while(true);
    }
}
