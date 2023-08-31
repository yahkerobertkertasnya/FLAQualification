package worker.server;

import worker.Worker;
import worker.cook.Cook;

public class ServerServeFoodState extends ServerBaseState {
    int number;
    @Override
    public void enterState(Server currServe) {
        this.name = String.format("serve food <%s>", currServe.getCustomer().getName());
        this.number = 0;
    }

    @Override
    public void updateState(Server currServe) {
        number += 1;

        if(number == 2){
            currServe.getMediator().finishServing(currServe);
        }
    }
}
