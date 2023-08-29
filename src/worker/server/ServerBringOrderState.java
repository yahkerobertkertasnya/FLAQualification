package worker.server;

public class ServerBringOrderState extends ServerBaseState {
    @Override
    public void enterState(Server currServe) {
        this.name = "bring order";
    }

    @Override
    public void updateState(Server currServer) {

    }

}
