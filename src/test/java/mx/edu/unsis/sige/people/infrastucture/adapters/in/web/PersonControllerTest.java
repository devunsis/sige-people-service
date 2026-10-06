package mx.edu.unsis.sige.people.infrastructure.adapters.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import mx.edu.unsis.sige.people.domain.model.Person;
import mx.edu.unsis.sige.people.domain.model.valueobjects.Curp;
import mx.edu.unsis.sige.people.domain.ports.in.CreateStaffPersonUseCase;
import mx.edu.unsis.sige.people.domain.ports.in.CreateStudentPersonUseCase;
import mx.edu.unsis.sige.people.domain.ports.in.FindPersonByIdUseCase;
import mx.edu.unsis.sige.people.infrastructure.adapters.in.web.dto.CreatePersonRequest;
import mx.edu.unsis.sige.people.infrastructure.adapters.in.web.dto.PersonResponse;
import mx.edu.unsis.sige.people.infrastructure.adapters.in.web.mapper.PersonWebMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private CreateStaffPersonUseCase createStaffPersonUseCase;

    @Mock
    private CreateStudentPersonUseCase createStudentPersonUseCase;

    @Mock
    private FindPersonByIdUseCase findPersonByIdUseCase;

    @Mock
    private PersonWebMapper personWebMapper;

    @InjectMocks
    private PersonController personController;

    private CreatePersonRequest validRequest;
    private Person personDomain;
    private PersonResponse personResponse;
    private UUID personId;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

        personId = UUID.randomUUID();

        validRequest = CreatePersonRequest.builder()
                .curp("GARI950515HOCRRM01")
                .firstName("Irving Efren")
                .firstSurname("García")
                .secondSurname("Ramos")
                .birthDate(LocalDate.of(1995, 5, 15))
                .build();

        personDomain = Person.builder()
                .id(personId)
                .curp(new Curp("GARI950515HOCRRM01"))
                .firstName("Irving Efren")
                .firstSurname("García")
                .secondSurname("Ramos")
                .birthDate(LocalDate.of(1995, 5, 15))
                .build();

        personResponse = PersonResponse.builder()
                .id(personId)
                .curp("GARI950515HOCRRM01")
                .firstName("Irving Efren")
                .firstSurname("García")
                .secondSurname("Ramos")
                .birthDate(LocalDate.of(1995, 5, 15))
                .build();
    }

// 1. POST /api/v1/people/staff
    @Test
    @DisplayName("POST /api/v1/people/staff - Debe retornar 201 CREATED al registrar personal")
    void shouldCreateStaffPersonSuccessfully() throws Exception {
        when(personWebMapper.toDomain(any(CreatePersonRequest.class))).thenReturn(personDomain);
        when(createStaffPersonUseCase.createStaffPerson(any(Person.class))).thenReturn(personDomain);
        when(personWebMapper.toResponse(any(Person.class))).thenReturn(personResponse);

        mockMvc.perform(post("/api/v1/people/staff")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201)) // <--- Cambiar 'code' por 'status' (o eliminar si no aplica)
                .andExpect(jsonPath("$.message").value("Personal registrado correctamente"))
                .andExpect(jsonPath("$.data.id").value(personId.toString()));

        verify(createStaffPersonUseCase).createStaffPerson(any(Person.class));
    }

    // 2. POST /api/v1/people/students
    @Test
    @DisplayName("POST /api/v1/people/students - Debe retornar 201 CREATED al registrar estudiante")
    void shouldCreateStudentPersonSuccessfully() throws Exception {
        when(personWebMapper.toDomain(any(CreatePersonRequest.class))).thenReturn(personDomain);
        when(createStudentPersonUseCase.createStudentPerson(any(Person.class))).thenReturn(personDomain);
        when(personWebMapper.toResponse(any(Person.class))).thenReturn(personResponse);

        mockMvc.perform(post("/api/v1/people/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.message").value("Estudiante registrado correctamente")) // <--- Cambio aquí
                .andExpect(jsonPath("$.data.id").value(personId.toString()));

        verify(createStudentPersonUseCase).createStudentPerson(any(Person.class));
    }

    // 3. POST /api/v1/people/applicants
    @Test
    @DisplayName("POST /api/v1/people/applicants - Debe retornar 201 CREATED al registrar aspirante")
    void shouldCreateApplicantPersonSuccessfully() throws Exception {
        when(personWebMapper.toDomain(any(CreatePersonRequest.class))).thenReturn(personDomain);
        when(createStudentPersonUseCase.createStudentPerson(any(Person.class))).thenReturn(personDomain);
        when(personWebMapper.toResponse(any(Person.class))).thenReturn(personResponse);

        mockMvc.perform(post("/api/v1/people/applicants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201)) // <--- Cambiar 'code' por 'status'
                .andExpect(jsonPath("$.message").value("Aspirante registrado correctamente"))
                .andExpect(jsonPath("$.data.id").value(personId.toString()));

        verify(createStudentPersonUseCase).createStudentPerson(any(Person.class));
    }

    // 4. GET /api/v1/people/{id} - Encontrado
    @Test
    @DisplayName("GET /api/v1/people/{id} - Debe retornar 200 OK cuando la persona existe")
    void shouldReturnPersonByIdWhenExists() throws Exception {
        when(findPersonByIdUseCase.findById(personId)).thenReturn(Optional.of(personDomain));
        when(personWebMapper.toResponse(personDomain)).thenReturn(personResponse);

        mockMvc.perform(get("/api/v1/people/{id}", personId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200)) // <--- Cambiar 'code' por 'status'
                .andExpect(jsonPath("$.message").value("Persona encontrada"))
                .andExpect(jsonPath("$.data.id").value(personId.toString()));

        verify(findPersonByIdUseCase).findById(personId);
    }

    // 5. GET /api/v1/people/{id} - No Encontrado
    @Test
    @DisplayName("GET /api/v1/people/{id} - Debe retornar 404 NOT FOUND cuando la persona no existe")
    void shouldReturn404WhenPersonNotFound() throws Exception {
        UUID unknownId = UUID.randomUUID();
        when(findPersonByIdUseCase.findById(unknownId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/people/{id}", unknownId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404)) // <--- Cambiar 'code' por 'status'
                .andExpect(jsonPath("$.message").value("Persona no encontrada con ID: " + unknownId));

        verify(findPersonByIdUseCase).findById(unknownId);
    }
}