package worker.server;

public class ServerTakeOrderState extends ServerBaseState {
    @Override
    public void enterState(Server currServe) {
        this.name = "take order";
    }

    @Override
    public void updateState(Server currServer) {

    }
}
