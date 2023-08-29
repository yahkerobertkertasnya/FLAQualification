package factory;

import Mediator.Mediator;
import worker.Worker;

public interface WorkerFactory {
    public Worker createWorker(Mediator mediator);
}
