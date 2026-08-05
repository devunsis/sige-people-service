package mx.edu.unsis.sige.shared.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

@AutoConfigureMockMvc(addFilters = false)
public abstract class AbstractIntegrationTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    void setUpSecurityContext() {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("test_user", null,
                Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}