package worker.cook;

import customer.Customer;

public class CookCookState extends CookBaseState {
    int number;
    @Override
    public void enterState(Cook currCook) {
        this.name = String.format("cook <%s>", currCook.getCustomer().getName());
        this.number = 0;
    }

    @Override
    public void updateState(Cook currCook) {
        number += 1;
        if(number == (6 - currCook.getSpeed())) {
            currCook.switchState(currCook.doneState);
        }
    }
}
