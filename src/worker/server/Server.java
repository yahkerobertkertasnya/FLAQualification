package worker.server;

import Mediator.Mediator;
import worker.Worker;
import worker.WorkerState;

public class Server extends Worker {
    protected ServerIdleState idleState;
    protected ServerBringOrderState bringOrderState;
    protected ServerServeFoodState serveOrderState;
    protected ServerTakeOrderState takeOrderState;
    protected ServerWaitCookState waitCookState;
    public Server(Mediator mediator){
        super(mediator);
        this.idleState = new ServerIdleState();
        this.bringOrderState = new ServerBringOrderState();
        this.serveOrderState = new ServerServeFoodState();
        this.takeOrderState = new ServerTakeOrderState();
        this.waitCookState = new ServerWaitCookState();

        this.phase = this.idleState;
        ((ServerBaseState)this.phase).enterState(this);
        this.thread.start();
        setPaused(true);
    }

    @Override
    public void updateState() {

    }

    @Override
    public void switchState(WorkerState nextState) {
        this.phase = nextState;
        ((ServerBaseState)this.phase).enterState(this);
    }

    @Override
    public void run() {
        do {
            if(this.isPaused) continue;
            this.updateState();
            System.out.println("server");
            if(this.isStopped) break;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while(true);
    }
}
