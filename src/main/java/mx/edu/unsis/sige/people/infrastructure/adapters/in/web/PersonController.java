package mx.edu.unsis.sige.people.infrastructure.adapters.in.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.unsis.sige.people.domain.model.Person;
import mx.edu.unsis.sige.people.domain.ports.in.CreateStaffPersonUseCase;
import mx.edu.unsis.sige.people.domain.ports.in.CreateStudentPersonUseCase;
import mx.edu.unsis.sige.people.domain.ports.in.FindPersonByIdUseCase;
import mx.edu.unsis.sige.people.infrastructure.adapters.in.web.dto.CreatePersonRequest;
import mx.edu.unsis.sige.people.infrastructure.adapters.in.web.dto.PersonResponse;
import mx.edu.unsis.sige.people.infrastructure.adapters.in.web.mapper.PersonWebMapper;
import mx.edu.unsis.sige.shared.infrastructure.adapters.in.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/people")
@RequiredArgsConstructor
public class PersonController {

        private final CreateStaffPersonUseCase createStaffPersonUseCase;
        private final CreateStudentPersonUseCase createStudentPersonUseCase;
        private final FindPersonByIdUseCase findPersonByIdUseCase;
        private final PersonWebMapper personWebMapper;

        @PostMapping("/staff")
        public ResponseEntity<BaseResponse<PersonResponse>> createStaffPerson(
                        @Valid @RequestBody CreatePersonRequest request) {

                Person personDomain = personWebMapper.toDomain(request);
                Person savedPerson = createStaffPersonUseCase.createStaffPerson(personDomain);
                PersonResponse response = personWebMapper.toResponse(savedPerson);

                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(BaseResponse.success(response, "Personal registrado correctamente",
                                                HttpStatus.CREATED.value()));
        }

        @PostMapping("/students")
        public ResponseEntity<BaseResponse<PersonResponse>> createStudentPerson(
                        @Valid @RequestBody CreatePersonRequest request) {

                Person personDomain = personWebMapper.toDomain(request);
                Person savedPerson = createStudentPersonUseCase.createStudentPerson(personDomain);
                PersonResponse response = personWebMapper.toResponse(savedPerson);

                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(BaseResponse.success(response, "Estudiante registrado correctamente",
                                HttpStatus.CREATED.value()));
        }

        @PostMapping("/applicants")
        public ResponseEntity<BaseResponse<PersonResponse>> createApplicantPerson(
                @Valid @RequestBody CreatePersonRequest request) {

                Person personDomain = personWebMapper.toDomain(request);
                // Reutiliza la misma lógica de negocio de estudiante/aspirante
                Person savedPerson = createStudentPersonUseCase.createStudentPerson(personDomain);
                PersonResponse response = personWebMapper.toResponse(savedPerson);

                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(BaseResponse.success(response, "Aspirante registrado correctamente",
                                HttpStatus.CREATED.value()));
        }

        @GetMapping("/{id}")
        public ResponseEntity<BaseResponse<PersonResponse>> getPersonById(@PathVariable UUID id) {
                return findPersonByIdUseCase.findById(id).map(person -> ResponseEntity.ok(
                                BaseResponse.success(personWebMapper.toResponse(person), "Persona encontrada")))
                                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                                                .body(BaseResponse.error("Persona no encontrada con ID: " + id,
                                                                HttpStatus.NOT_FOUND.value())));
        }
}
