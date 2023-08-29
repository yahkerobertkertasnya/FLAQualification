package factory;

import Mediator.Mediator;
import restaurant.CurrentRestaurant;
import worker.Worker;
import worker.cook.Cook;

public class CookFactory implements WorkerFactory {

    @Override
    public Worker createWorker(Mediator mediator) {
        return new Cook(mediator);
    }
}
