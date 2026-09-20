package mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mx.edu.unsis.sige.people.domain.ports.out.OutboxEventPort;
import mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.entity.OutboxEventEntity;
import mx.edu.unsis.sige.people.infrastructure.adapters.out.persistence.repository.OutboxEventRepository;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OutboxAdapter implements OutboxEventPort {

    private final OutboxEventRepository outboxEventRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void saveEvent(String aggregateType, UUID aggregateId, String eventType, Object payload) {
        try {
            String jsonPayload = objectMapper.writeValueAsString(payload);

            OutboxEventEntity outboxEvent = OutboxEventEntity.builder()
                    .id(UUID.randomUUID())
                    .aggregateType(aggregateType)
                    .aggregateId(aggregateId.toString())
                    .eventType(eventType)
                    .payload(jsonPayload)
                    .status("PENDING")
                    .createdAt(OffsetDateTime.now())
                    .build();

            outboxEventRepository.save(outboxEvent);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing outbox payload for event " + eventType, e);
        }
    }
}