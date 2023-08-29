package factory;

import Mediator.Mediator;
import worker.Worker;
import worker.server.Server;

public class ServerFactory implements WorkerFactory {
    @Override
    public Worker createWorker(Mediator mediator) {
        return new Server(mediator);
    }
}
