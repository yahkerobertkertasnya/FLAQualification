package worker.cook;

import Mediator.Mediator;
import worker.Worker;
import worker.WorkerState;

import javax.print.attribute.standard.Media;

public class Cook extends Worker {
    protected CookCookState cookState;
    protected CookIdleState idleState;
    protected CookDoneState doneState;

    public Cook(Mediator mediator){
        super(mediator);
        this.cookState = new CookCookState();
        this.idleState = new CookIdleState();
        this.doneState = new CookDoneState();

        this.phase = this.idleState;
        ((CookBaseState)this.phase).enterState(this);
        this.thread.start();
        setPaused(true);
    }

    @Override
    public void updateState() {

    }

    @Override
    public void switchState(WorkerState nextState) {
        this.phase = nextState;
        ((CookBaseState)this.phase).enterState(this);
    }

    @Override
    public void run() {
        do {
            if(this.isPaused) continue;
            this.updateState();
            System.out.println("cook");
            if(this.isStopped) break;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while(true);
    }
}
