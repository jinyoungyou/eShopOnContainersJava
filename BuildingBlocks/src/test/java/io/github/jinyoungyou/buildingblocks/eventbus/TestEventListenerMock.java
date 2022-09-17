package io.github.jinyoungyou.buildingblocks.eventbus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
public class TestEventListenerMock {

    private TestEvent receivedEvent;

    @Subscribe
    @AllowConcurrentEvents
    void testHandler(TestEvent receivedEvent) {
        this.receivedEvent = receivedEvent;
    }

    @AllArgsConstructor
    @ToString
    static class TestEvent extends IntegrationEvent {

        String userId;
    }
}
