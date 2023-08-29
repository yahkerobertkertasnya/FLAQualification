package observer;

import java.util.Random;

public class CustomerGenerator implements Observer {

    private Random rng;
    public CustomerGenerator() {
        rng = new Random();
    }
    @Override
    public boolean generateCustomer() {
        return rng.nextInt(5) == 4;
    }
}
