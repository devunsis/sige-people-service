package mx.edu.unsis.sige.shared.domain.event;

import org.slf4j.MDC;
import java.time.LocalDateTime;
import java.util.UUID;

public class OutboxEvent {
    private String id;
    private String aggregateType;
    private String aggregateId;
    private String eventType;
    private String payload;
    private String correlationId;
    private LocalDateTime createdAt;

    // Constructor estructurado que captura automáticamente el ID del hilo actual
    public OutboxEvent(String aggregateType, String aggregateId, String eventType, String payload) {
        this.id = UUID.randomUUID().toString();
        this.aggregateType = aggregateType;
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.payload = payload;
        this.createdAt = LocalDateTime.now();

        // Captura el correlationId activo del MDC. Si es un evento del sistema sin
        // petición, pone "SYSTEM"
        String currentCorrelationId = MDC.get("correlationId");
        this.correlationId = currentCorrelationId != null ? currentCorrelationId : "SYSTEM";
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getAggregateType() {
        return aggregateType;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getPayload() {
        return payload;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
