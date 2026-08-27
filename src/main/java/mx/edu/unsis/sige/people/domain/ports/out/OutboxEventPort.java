package mx.edu.unsis.sige.people.domain.ports.out;

import java.util.UUID;

public interface OutboxEventPort {

    void saveEvent(String aggregateType, UUID aggregateId, String eventType, String payload);
}