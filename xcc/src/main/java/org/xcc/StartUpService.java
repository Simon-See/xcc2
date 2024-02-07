package org.xcc;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class StartUpService {

    private final QueueProcessor queueProcessor;

    public StartUpService(QueueProcessor queueProcessor) {
        this.queueProcessor = queueProcessor;
    }

    void onStart(@Observes StartupEvent ev) {
        queueProcessor.start();
    }
}