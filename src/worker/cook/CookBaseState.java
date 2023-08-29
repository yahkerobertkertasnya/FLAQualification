package worker.cook;

import customer.Customer;
import worker.WorkerState;

public abstract class CookBaseState extends WorkerState {
    public abstract void enterState(Cook currCook);
    public abstract void updateState(Cook currCook);

}
