package worker;

import customer.Customer;
import worker.cook.Cook;

public abstract class WorkerState {
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
