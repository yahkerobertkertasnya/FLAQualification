package factory;

import Mediator.PeopleMediator;
import worker.Worker;
import worker.server.Server;

public class ServerFactory implements WorkerFactory {
    @Override
    public Worker createWorker(PeopleMediator mediator) {
        return new Server(mediator);
    }
}
