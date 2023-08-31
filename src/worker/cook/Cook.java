package worker.cook;

import Mediator.PeopleMediator;
import worker.Worker;
import worker.WorkerState;

public class Cook extends Worker {
    private int skill;
    public CookCookState cookState;
    public CookIdleState idleState;
    public CookDoneState doneState;

    public Cook(PeopleMediator mediator){
        super(mediator);
        this.cookState = new CookCookState();
        this.idleState = new CookIdleState();
        this.doneState = new CookDoneState();
        this.mediator.register(this);
        this.phase = this.idleState;
        ((CookBaseState)this.phase).enterState(this);
        this.thread.start();
        this.skill = 1;
        setPaused(true);
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    @Override
    public void updateState() {
        ((CookBaseState)this.phase).updateState(this);
    }

    @Override
    public void switchState(WorkerState nextState) {
        this.phase = nextState;
        ((CookBaseState)this.phase).enterState(this);
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
            this.updateState();
            if(this.isStopped) break;
        } while(true);
    }
}
