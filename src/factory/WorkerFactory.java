package factory;

import Mediator.PeopleMediator;
import worker.Worker;

public interface WorkerFactory {
    public Worker createWorker(PeopleMediator mediator);
}
