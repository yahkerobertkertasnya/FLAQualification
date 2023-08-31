package Mediator;

import customer.Customer;
import worker.Worker;

public interface Mediator {
    public void register(Worker worker);
    public void register(Customer customer);
    public void unregister(String name);
}
