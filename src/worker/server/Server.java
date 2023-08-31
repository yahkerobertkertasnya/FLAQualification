package worker.server;

import Mediator.PeopleMediator;
import worker.Worker;
import worker.WorkerState;

public class Server extends Worker {
    public ServerIdleState idleState;
    public ServerBringOrderState bringOrderState;
    public ServerServeFoodState serveFoodState;
    public ServerTakeOrderState takeOrderState;
    public ServerWaitCookState waitCookState;
    public Server(PeopleMediator mediator){
        super(mediator);
        this.idleState = new ServerIdleState();
        this.bringOrderState = new ServerBringOrderState();
        this.serveFoodState = new ServerServeFoodState();
        this.takeOrderState = new ServerTakeOrderState();
        this.waitCookState = new ServerWaitCookState();
        this.mediator.register(this);
        this.phase = this.idleState;
        ((ServerBaseState)this.phase).enterState(this);
        this.thread.start();
        setPaused(true);
    }

    @Override
    public void updateState() {
        ((ServerBaseState)this.phase).updateState(this);
    }

    @Override
    public void switchState(WorkerState nextState) {
        this.phase = nextState;
        ((ServerBaseState)this.phase).enterState(this);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(this.isPaused) continue;
            if(this.isStopped) break;
            this.updateState();

        } while(true);
    }
}
