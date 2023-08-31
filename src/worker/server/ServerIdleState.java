package worker.server;

public class ServerIdleState extends ServerBaseState {

    @Override
    public void enterState(Server currServe) {
        this.name = "idle";
    }

    @Override
    public void updateState(Server currServer) {
        currServer.getMediator().takeCustomerOrder(currServer);
    }
}
