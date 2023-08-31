package factory;

import Mediator.PeopleMediator;
import worker.Worker;
import worker.cook.Cook;

public class CookFactory implements WorkerFactory {

    @Override
    public Worker createWorker(PeopleMediator mediator) {
        return new Cook(mediator);
    }
}
