package worker.server;

import worker.WorkerState;

public abstract class ServerBaseState extends WorkerState {
    public abstract void enterState(Server currServe);
    public abstract void updateState(Server currServe);
}
