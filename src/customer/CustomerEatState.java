package customer;

public class CustomerEatState extends CustomerBaseState {

    int number;
    @Override
    public void enterState(Customer currCustomer) {
        this.name = "eat";
        this.number = 0;
    }

    @Override
    public void updateState(Customer currCustomer) {
        number += 1;
        if(number == 6) {
            currCustomer.finish();
        }
    }
}
