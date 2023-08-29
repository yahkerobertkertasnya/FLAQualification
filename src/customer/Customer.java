package customer;

import Mediator.Mediator;
import helper.NameGenerator;
import restaurant.CurrentRestaurant;

public class Customer implements Runnable {
    private String name;
    private int tolerance;
    private Thread thread;
    private CustomerBaseState phase;
    private boolean isPaused = false;
    private boolean isStop = false;
    private Mediator mediator;
    protected CustomerEatState eatState;
    protected CustomerOrderState orderState;
    protected CustomerOrderServerState orderServerState;
    protected CustomerWaitState waitState;
    protected CustomerWaitCookState waitCookState;
    protected CustomerWaitServerState waitServerState;

    public Customer(Mediator mediator){
        this.name = NameGenerator.generateName();
        this.tolerance = 20;
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
    }

    protected void switchState(CustomerBaseState newState){
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

    public void setPaused(boolean paused) {
        System.out.println("Customer " + this.name + " is paused" + paused);
        isPaused = paused;
    }

    public void leave(){
        this.thread.interrupt();
        this.isStop = true;
        CurrentRestaurant.getInstance().getCurrentRestaurant().removeCustomer(this.name);
        CurrentRestaurant.getInstance().getCurrentRestaurant().setScore(CurrentRestaurant.getInstance().getCurrentRestaurant().getScore() - 300);

    }

    @Override
    public void run() {
        while(true) {
            if(this.isStop) break;
            if(this.isPaused) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                continue;
            }
            phase.updateState(this);
        }
    }
}
