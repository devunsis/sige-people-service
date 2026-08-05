package mx.edu.unsis.sige.shared.infrastructure.config.metrics;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

    @Value("${spring.application.name:sige-backend-core}")
    private String applicationName;

    /**
     * Etiqueta globalmente todas las métricas exportadas con el nombre de la aplicación
     * para facilitar el filtrado multi-servicio en Grafana.
     */
    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application", applicationName);
    }

    /**
     * Habilita el uso de la anotación @Timed en métodos de servicios o controladores
     * para medir latencias automáticamente.
     */
    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }
}