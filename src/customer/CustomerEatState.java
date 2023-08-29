package customer;

public class CustomerEatState extends CustomerBaseState {

    @Override
    public void enterState(Customer currCustomer) {
        this.name = "eat";
    }

    @Override
    public void updateState(Customer currCustomer) {

    }
}
