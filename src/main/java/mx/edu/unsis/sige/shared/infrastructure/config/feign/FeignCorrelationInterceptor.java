package mx.edu.unsis.sige.shared.infrastructure.config.feign;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class FeignCorrelationInterceptor implements RequestInterceptor {

    private static final String CORRELATION_ID_HEADER = "X-Correlation-ID";
    private static final String MDC_CORRELATION_KEY = "correlationId";

    @Override
    public void apply(RequestTemplate template) {
        // 1. Extraer el ID del MDC del hilo actual
        String correlationId = MDC.get(MDC_CORRELATION_KEY);

        // 2. Si existe el contexto, se añade como header a la petición saliente
        if (StringUtils.hasText(correlationId)) {
            template.header(CORRELATION_ID_HEADER, correlationId);
        }
    }
}
