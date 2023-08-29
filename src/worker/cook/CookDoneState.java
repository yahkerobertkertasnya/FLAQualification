package worker.cook;

import customer.Customer;

public class CookDoneState extends CookBaseState {

    @Override
    public void enterState(Cook currCook) {
        this.name = "done";
    }

    @Override
    public void updateState(Cook currCook) {

    }
}
