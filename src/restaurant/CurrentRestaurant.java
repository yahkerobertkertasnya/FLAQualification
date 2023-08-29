package restaurant;

import customer.Customer;
import pages.PauseMenu;
import worker.cook.Cook;
import worker.server.Server;

public class CurrentRestaurant {

    private Restaurant currentRestaurant = null;
    private static volatile CurrentRestaurant instance = null;
    private CurrentRestaurant(){

    }
    public static CurrentRestaurant getInstance(){
        if (instance != null) return instance;

        synchronized (CurrentRestaurant.class) {
            if(instance == null) instance = new CurrentRestaurant();
        }
        return instance;
    }
    public Restaurant getCurrentRestaurant(){
        return currentRestaurant;
    }
    public void setCurrentRestaurant(Restaurant newRestaurant){
        currentRestaurant = newRestaurant;
    }
    public void closeCurrentRestaurant(){
        currentRestaurant = null;
    }
    public void pauseGame(){
        this.currentRestaurant.getGameInput().setStopped(true);
        this.currentRestaurant.switchState(this.currentRestaurant.getPausedState());

        for(Server server : this.currentRestaurant.getServers()){
            server.setPaused(true);
        }
        for(Cook cook : this.currentRestaurant.getCooks()){
            cook.setPaused(true);
        }
        for(Customer customer : this.currentRestaurant.getCustomers()){
            customer.setPaused(true);
        }
        new PauseMenu();
    }

    public void unpauseGame(){
        this.currentRestaurant.getGameInput().setStopped(false);
        this.currentRestaurant.switchState(this.currentRestaurant.getUnpausedState());

        for(Server server : this.currentRestaurant.getServers()){
            server.setPaused(false);
        }
        for(Cook cook : this.currentRestaurant.getCooks()){
            cook.setPaused(false);
        }
        for(Customer customer : this.currentRestaurant.getCustomers()){
            System.out.println(customer.getName());
            customer.setPaused(false);
        }
    }
    public void handleUpdate(){
        this.currentRestaurant.addCustomer();
    }

}
