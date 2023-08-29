package customer;

public class CustomerWaitState extends CustomerBaseState {

    @Override
    public void enterState(Customer currCustomer) {
        this.name = "Wait";
    }

    @Override
    public void updateState(Customer currCustomer) {

    }
}
