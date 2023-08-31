package customer;

public class CustomerWaitState extends CustomerBaseState {

    @Override
    public void enterState(Customer currCustomer) {
        this.name = "wait food";
    }

    @Override
    public void updateState(Customer currCustomer) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        currCustomer.setTolerance(currCustomer.getTolerance() - 1);
        if(currCustomer.getTolerance() == 0) {
            currCustomer.leave();
        }
    }
}
