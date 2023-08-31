package customer;

import Mediator.PeopleMediator;
import helper.NameGenerator;
import restaurant.CurrentRestaurant;
import worker.Worker;
import worker.cook.Cook;

public class Customer implements Runnable {
    private String name;
    private int tolerance;
    private Thread thread;
    private CustomerBaseState phase;
    private boolean isPaused = false;
    private boolean isStop = false;
    private PeopleMediator mediator;
    private Worker worker;
    private int score;
    public CustomerEatState eatState;
    public CustomerOrderState orderState;
    public CustomerOrderServerState orderServerState;
    public CustomerWaitState waitState;
    public CustomerWaitCookState waitCookState;
    public CustomerWaitServerState waitServerState;

    public Customer(PeopleMediator mediator){
        this.name = NameGenerator.generateName();
        this.tolerance = 2;
        this.eatState = new CustomerEatState();
        this.orderState = new CustomerOrderState();
        this.orderServerState = new CustomerOrderServerState();
        this.waitState = new CustomerWaitState();
        this.waitCookState = new CustomerWaitCookState();
        this.waitServerState = new CustomerWaitServerState();
        this.phase = this.orderState;
        this.phase.enterState(this);
        this.thread = new Thread(this);
        this.thread.start();
        this.mediator = mediator;
        this.mediator.register(this);
        this.score = 0;

    }

    public void switchState(CustomerBaseState newState){
        this.phase = newState;
        this.phase.enterState(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTolerance() {
        return tolerance;
    }

    public void setTolerance(int tolerance) {
        this.tolerance = tolerance;
    }

    public CustomerBaseState getPhase() {
        return phase;
    }

    public void setPhase(CustomerBaseState phase) {
        this.phase = phase;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public void leave(){
        this.isStop = true;
        CurrentRestaurant.getInstance().getCurrentRestaurant().removeCustomer(this.name);
        CurrentRestaurant.getInstance().getCurrentRestaurant().setScore(CurrentRestaurant.getInstance().getCurrentRestaurant().getScore() - 300);
        this.mediator.leaveCustomer(this);
        this.mediator.unregister(this.name);
    }

    public void finish() {
        this.isStop = true;
        CurrentRestaurant.getInstance().getCurrentRestaurant().removeCustomer(this.name);
        System.out.println(this.score);
        CurrentRestaurant.getInstance().getCurrentRestaurant().setScore(CurrentRestaurant.getInstance().getCurrentRestaurant().getScore() + this.score);
        CurrentRestaurant.getInstance().getCurrentRestaurant().setMoney(CurrentRestaurant.getInstance().getCurrentRestaurant().getMoney() + this.score);
        this.mediator.unregister(this.name);
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(this.isPaused) continue;
            if(this.isStop) break;
            phase.updateState(this);
        }
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }
}
