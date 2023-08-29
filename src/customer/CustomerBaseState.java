package customer;

import customer.Customer;

public abstract class CustomerBaseState {
    protected String name;
    public abstract void enterState(Customer currCustomer);
    public abstract void updateState(Customer currCustomer);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
