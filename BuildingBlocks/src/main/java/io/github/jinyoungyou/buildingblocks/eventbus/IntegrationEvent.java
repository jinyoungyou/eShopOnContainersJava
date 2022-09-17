package io.github.jinyoungyou.buildingblocks.eventbus;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public abstract class IntegrationEvent {

    final UUID id;
    final LocalDateTime createdAt;

    protected IntegrationEvent() {
        id = UUID.randomUUID();
        createdAt = LocalDateTime.now();
    }
}