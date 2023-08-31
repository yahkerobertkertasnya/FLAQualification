package restaurant;

import Mediator.PeopleMediator;
import customer.Customer;
import factory.CookFactory;
import factory.ServerFactory;
import helper.GameInput;
import observer.CustomerGenerator;
import observer.Observer;
import observer.Subject;
import worker.cook.Cook;
import worker.server.Server;

import java.util.Vector;

public class Restaurant implements Subject {
    private String name;
    private int chairCount;
    private int money;
    private int score;
    private Vector<Server> servers;
    private Vector<Cook> cooks;
    private Vector<Customer> customers;
    private Vector<Observer> subscribe;
    private ServerFactory serverFactory;
    private CookFactory cookFactory;
    private RestaurantBaseState currState;
    private RestaurantPausedState pausedState;
    private RestaurantUnpausedState unpausedState;
    private GameInput gameInput;
    private PeopleMediator mediator;

    public Restaurant(String name) {
        this.name = name;
        this.chairCount = 4;
        this.money = 1300;
        this.score = 0;
        this.servers = new Vector<>();
        this.cooks = new Vector<>();
        this.customers = new Vector<>();
        this.subscribe = new Vector<>();
        this.serverFactory = new ServerFactory();
        this.cookFactory = new CookFactory();
        this.pausedState = new RestaurantPausedState();
        this.unpausedState = new RestaurantUnpausedState();
        this.currState = unpausedState;
        this.gameInput = new GameInput();
        this.mediator = new PeopleMediator();

        addCook();
        addCook();
        addServer();
        addServer();
        createGenerator();
    }

    public void switchState(RestaurantBaseState nextState){
        currState = nextState;
    }
    protected void addCook(){
        Cook cook = (Cook) cookFactory.createWorker(this.mediator);
        cooks.add(cook);
    }

    protected void addServer(){
        Server server = (Server) serverFactory.createWorker(this.mediator);
        servers.add(server);
    }

    protected void addCustomer() {
        if(chairCount == customers.size()) return;
        if(subscribe.get(0).generateCustomer()) { //TODO
            Customer customer = new Customer(this.mediator);
            customers.add(customer);
        }
    }

    public void removeCustomer(String name) {
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i).getName().equals(name)) {
                customers.remove(i);
                break;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Vector<Customer> customers) {
        this.customers = customers;
    }

    public int getChairCount() {
        return chairCount;
    }

    public void setChairCount(int chairCount) {
        this.chairCount = chairCount;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Vector<Server> getServers() {
        return servers;
    }

    public void setServers(Vector<Server> servers) {
        this.servers = servers;
    }

    public Vector<Cook> getCooks() {
        return cooks;
    }

    public void setCooks(Vector<Cook> cooks) {
        this.cooks = cooks;
    }

    public ServerFactory getServerFactory() {
        return serverFactory;
    }

    public void setServerFactory(ServerFactory serverFactory) {
        this.serverFactory = serverFactory;
    }

    public CookFactory getCookFactory() {
        return cookFactory;
    }

    public void setCookFactory(CookFactory cookFactory) {
        this.cookFactory = cookFactory;
    }

    public GameInput getGameInput() {
        return gameInput;
    }

    public void setGameInput(GameInput gameInput) {
        this.gameInput = gameInput;
    }

    public RestaurantBaseState getCurrState() {
        return currState;
    }

    public void setCurrState(RestaurantBaseState currState) {
        this.currState = currState;
    }

    public RestaurantPausedState getPausedState() {
        return pausedState;
    }

    public void setPausedState(RestaurantPausedState pausedState) {
        this.pausedState = pausedState;
    }

    public RestaurantUnpausedState getUnpausedState() {
        return unpausedState;
    }

    public void setUnpausedState(RestaurantUnpausedState unpausedState) {
        this.unpausedState = unpausedState;
    }

    public String upgradeChair(){
        if(money < 100 * chairCount) return "Not enough money";
        chairCount += 1;
        money -= 100 * chairCount;
        return "Chair upgraded";
    }

    public String hireServer(){
        if(money < 150 * servers.size()) return "Not enough money";
        addServer();
        money -= 150 * servers.size();
        return "Server hired";
    }

    public String hireCook(){
        if(money < 200 * cooks.size()) return "Not enough money";
        addCook();
        money -= 200 * cooks.size();
        return "Cook hired";
    }

    public String upgradeServer(int index){
        if(money < 150) return "Not enough money";
        Server currServer = servers.get(index);

        int speed = currServer.getSpeed();

        if(speed == 5) return "Already max speed";
        currServer.setSpeed(speed + 1);
        money -= 150;

        return "Server speed upgraded";
    }

    public String upgradeCook(int index, String type){
        if(money < 150) return "Not enough money";
        Cook currCook = cooks.get(index);
        int speed = currCook.getSpeed();

        if(type.equals("speed")) {
            if(currCook.getSpeed() == 5) return "Already max speed";
            currCook.setSpeed(currCook.getSpeed() + 1);
            money -= 150;

            return "Server speed upgraded";
        }
        else if (type.equals("skill")) {
            if(currCook.getSkill() == 5) return "Already max skill";
            currCook.setSkill(currCook.getSkill() + 1);
            money -= 150;

            return "Server skill upgraded";
        }
        if(speed == 5) return "Already max speed";
        currCook.setSpeed(speed + 1);
        money -= 150;

        return "Cook speed upgraded";
    }


    @Override
    public void createGenerator() {
        subscribe.add(new CustomerGenerator());
    }
}
