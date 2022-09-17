package io.github.jinyoungyou.buildingblocks.eventbus;

import static org.assertj.core.api.Java6Assertions.assertThat;

import io.github.jinyoungyou.buildingblocks.eventbus.TestEventListenerMock.TestEvent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class LocalEventBusTest {

    private IntegrationEventBus eventBus;

    @BeforeEach
    void setUp() {
        eventBus = LocalEventBus.newSyncEventBus();
    }

    @Test
    void IntegrationEvent_구독_테스트() {
        TestEventListenerMock testEventListener = new TestEventListenerMock();
        eventBus.register(testEventListener);

        TestEvent testEvent = new TestEvent("test-user");

        eventBus.post(testEvent);

        assertThat(testEventListener.getReceivedEvent()).isEqualTo(testEvent);
    }

    @Test
    void IntegrationEventListener_해지_테스트() {
        TestEventListenerMock testEventListener = new TestEventListenerMock();
        eventBus.register(testEventListener);
        eventBus.unregister(testEventListener);

        TestEvent testEvent = new TestEvent("test-user");

        eventBus.post(testEvent);

        assertThat(testEventListener.getReceivedEvent()).isNotEqualTo(testEvent);
    }
}