package worker.server;

import customer.Customer;

public class ServerTakeOrderState extends ServerBaseState {
    int number;
    @Override
    public void enterState(Server currServe) {
        this.name = String.format("take order <%s>", currServe.getCustomer().getName());
        this.number = 0;
    }

    @Override
    public void updateState(Server currServer) {
        number += 1;
        if(number == (6 - currServer.getSpeed())) {
            Customer currCustomer = currServer.getCustomer();
            currCustomer.switchState(currCustomer.waitState);
            currCustomer.setWorker(null);
            currServer.switchState(currServer.waitCookState);
        }
    }
}
