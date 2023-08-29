package restaurant;

import worker.server.Server;

public abstract class RestaurantBaseState {
    public abstract void updateState(Server currServe);
}
