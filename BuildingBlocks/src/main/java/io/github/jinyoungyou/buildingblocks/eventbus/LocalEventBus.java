package io.github.jinyoungyou.buildingblocks.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class LocalEventBus implements IntegrationEventBus {

    private static final String BUS_IDENTIFIER = "IntegrationEventBus";
    private final EventBus eventBus;

    public static LocalEventBus newSyncEventBus() {
        return new LocalEventBus(new EventBus(BUS_IDENTIFIER));
    }

    public static LocalEventBus newAsyncEventBus(int nThreads) {
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);

        return new LocalEventBus(new AsyncEventBus(BUS_IDENTIFIER, executor));
    }

    @Override
    public void post(IntegrationEvent event) {
        eventBus.post(event);
    }

    @Override
    public void register(Object listener) {
        eventBus.register(listener);
    }

    @Override
    public void unregister(Object listener) {
        eventBus.unregister(listener);
    }

    private LocalEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }
}
