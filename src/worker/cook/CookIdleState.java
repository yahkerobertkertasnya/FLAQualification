package worker.cook;

import customer.Customer;

public class CookIdleState extends CookBaseState {
    @Override
    public void enterState(Cook currCook) {
        this.name = "idle";
    }
    @Override
    public void updateState(Cook currCook) {

    }
}
