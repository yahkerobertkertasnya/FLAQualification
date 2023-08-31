package worker.cook;

import customer.Customer;

public class CookDoneState extends CookBaseState {

    @Override
    public void enterState(Cook currCook) {
        this.name = String.format("done <%s>", currCook.getCustomer().getName());
    }

    @Override
    public void updateState(Cook currCook) {
        currCook.getMediator().finishCooking(currCook);
    }
}
