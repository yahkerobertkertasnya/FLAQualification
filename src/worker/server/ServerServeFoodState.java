package worker.server;

import worker.Worker;
import worker.cook.Cook;

public class ServerServeFoodState extends ServerBaseState {

    @Override
    public void enterState(Server currServe) {
        this.name = "serve food";
    }

    @Override
    public void updateState(Server currServe) {

    }
}
