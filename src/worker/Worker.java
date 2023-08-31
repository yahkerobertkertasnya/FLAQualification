package worker;

import Mediator.PeopleMediator;
import customer.Customer;
import helper.NameGenerator;

public abstract class Worker implements Runnable {
    protected String name;
    protected PeopleMediator mediator;
    protected boolean isStopped;
    protected boolean isPaused;
    protected WorkerState phase;
    protected int speed;
    protected Thread thread;
    protected Customer customer;

    public Worker(PeopleMediator mediator){
        this.name = NameGenerator.generateName();
        this.speed = 1;
        this.isStopped = false;
        this.thread = new Thread(this);
        this.mediator = mediator;
    }
    public abstract void updateState();
    public abstract void switchState(WorkerState nextState);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorkerState getPhase() {
        return phase;
    }

    public void setPhase(WorkerState phase) {
        this.phase = phase;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isStopped() {
        return isStopped;
    }

    public void setStopped(boolean stopped) {
        isStopped = stopped;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public Thread getThread(){
        return thread;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public PeopleMediator getMediator() {
        return mediator;
    }

    public void setMediator(PeopleMediator mediator) {
        this.mediator = mediator;
    }
}
