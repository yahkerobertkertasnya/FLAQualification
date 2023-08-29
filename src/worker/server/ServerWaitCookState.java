package worker.server;

public class ServerWaitCookState extends ServerBaseState {
    @Override
    public void enterState(Server currServe) {
        this.name = "wait cook";
    }

    @Override
    public void updateState(Server currServer) {

    }
}
