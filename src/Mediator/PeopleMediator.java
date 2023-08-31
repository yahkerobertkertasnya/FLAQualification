package Mediator;

import customer.Customer;
import customer.CustomerOrderState;
import worker.Worker;
import worker.cook.Cook;
import worker.cook.CookDoneState;
import worker.cook.CookIdleState;
import worker.server.Server;
import worker.server.ServerBaseState;
import worker.server.ServerIdleState;
import worker.server.ServerWaitCookState;

import java.util.Vector;

public class PeopleMediator implements Mediator {
    Vector<Customer> customers;
    Vector<Server> servers;
    Vector<Cook> cooks;
    public PeopleMediator(){
        this.customers = new Vector<>();
        this.servers = new Vector<>();
        this.cooks = new Vector<>();
    }


    @Override
    public void register(Worker worker) {
        if(worker instanceof Server) {
            servers.add((Server) worker);
        }
        else if(worker instanceof Cook){
            cooks.add((Cook) worker);
        }
    }

    @Override
    public void register(Customer customer) {
        customers.add(customer);
    }

    @Override
    public void unregister(String name) {
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i).getName().equals(name)){
                customers.remove(i);
                return;
            }
        }
    }

    public void takeCustomerOrder(Server server){
        synchronized (customers) {
            for(Customer customer : customers){
                if(customer.getPhase() instanceof CustomerOrderState){
                    if(server.getPhase() instanceof ServerIdleState) {
                        customer.setWorker(server);
                        customer.switchState(customer.orderServerState);
                        server.setCustomer(customer);
                        server.switchState(server.takeOrderState);
                        break;
                    }
                }
            }
        }
    }

    public void finishCustomerOrder(Server server){
        synchronized (cooks) {
            Customer currCustomer = server.getCustomer();
            for(Cook cook : cooks){
                if(cook.getPhase() instanceof CookIdleState){
                    cook.setCustomer(currCustomer);
                    cook.switchState(cook.cookState);
                    currCustomer.setWorker(cook);
                    currCustomer.switchState(currCustomer.waitCookState);
                    server.switchState(server.idleState);
                    return;
                }
//                if(cook.getPhase() instanceof CookDoneState){
//                    cook.setCustomer(currCustomer);
//                    cook.switchState(cook.cookState);
//                    currCustomer.setWorker(cook);
//                    currCustomer.switchState(currCustomer.waitCookState);
//                    server.switchState(server.serveFoodState);
//                    return;
//                }
            }
        }
    }

    public void finishCooking(Cook cook) {
        synchronized (servers) {
            Customer currCustomer = cook.getCustomer();
            currCustomer.setScore(30 * cook.getSkill());
            for(Server server : servers){
                if(server.getPhase() instanceof ServerIdleState){
                    server.setCustomer(currCustomer);
                    server.switchState(server.serveFoodState);
                    currCustomer.setWorker(server);
                    currCustomer.switchState(currCustomer.waitServerState);
                    cook.switchState(cook.idleState);
                    return;
                }
                if(server.getPhase() instanceof ServerWaitCookState){
                    cook.setCustomer(server.getCustomer());
                    cook.switchState(cook.cookState);
                    server.getCustomer().setWorker(cook);
                    server.getCustomer().switchState(server.getCustomer().waitCookState);
                    server.setCustomer(currCustomer);
                    server.switchState(server.serveFoodState);
                    currCustomer.setWorker(server);
                    currCustomer.switchState(currCustomer.waitServerState);
                    return;
                }
            }
            cook.switchState(cook.doneState);
        }
    }

    public void finishServing(Server server){
        Customer currCustomer = server.getCustomer();
        currCustomer.switchState(currCustomer.eatState);
        server.setCustomer(null);
        server.switchState(server.idleState);
    }

    public void leaveCustomer(Customer customer) {
        if(customer.getWorker() instanceof Server){
            (customer.getWorker()).switchState(((Server) customer.getWorker()).idleState);
            (customer.getWorker()).setCustomer(null);
        }
        if(customer.getWorker() instanceof Cook) {
            (customer.getWorker()).switchState(((Cook) customer.getWorker()).idleState);
            (customer.getWorker()).setCustomer(null);
        }
    }

}
