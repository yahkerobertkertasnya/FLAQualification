package worker.cook;

import customer.Customer;

public class CookCookState extends CookBaseState {
    @Override
    public void enterState(Cook currCook) {
        this.name = "idle";
    }

    @Override
    public void updateState(Cook currCook) {

    }
}
