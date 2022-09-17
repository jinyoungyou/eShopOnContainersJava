package io.github.jinyoungyou.buildingblocks.eventbus;

interface IntegrationEventBus {

    void post(IntegrationEvent event);

    void register(Object listener);

    void unregister(Object listener);
}