package customer;

public class CustomerOrderServerState extends CustomerBaseState {

    @Override
    public void enterState(Customer currCustomer) {
        this.name = String.format("order<%s>", currCustomer.getWorker().getName());
    }

    @Override
    public void updateState(Customer currCustomer) {

    }
}
