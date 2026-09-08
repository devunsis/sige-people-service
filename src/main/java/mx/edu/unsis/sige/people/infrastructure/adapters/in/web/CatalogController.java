package mx.edu.unsis.sige.people.infrastructure.adapters.in.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mx.edu.unsis.sige.people.domain.ports.in.FindCatalogsUseCase;
import mx.edu.unsis.sige.people.infrastructure.adapters.in.dto.response.ContactTypeResponse;
import mx.edu.unsis.sige.people.infrastructure.adapters.in.dto.response.DocumentTypeResponse;
import mx.edu.unsis.sige.people.infrastructure.adapters.in.dto.response.GenderResponse;
import mx.edu.unsis.sige.people.infrastructure.adapters.in.web.mapper.CatalogResponseMapper;
import mx.edu.unsis.sige.shared.infrastructure.adapters.in.dto.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Adaptador de entrada REST para la consulta estandarizada de los catálogos
 * básicos del dominio Person. Devuelve DTOs; nunca entidades JPA.
 *
 * <p>El {@code context-path} de la aplicación ya es {@code /api/v1}, por lo que
 * las rutas efectivas son {@code /api/v1/catalogs/...}.</p>
 */
@RestController
@RequestMapping("/catalogs")
@RequiredArgsConstructor
@Tag(name = "Catálogos", description = "Consulta de catálogos básicos del dominio Person")
public class CatalogController {

    private final FindCatalogsUseCase findCatalogsUseCase;
    private final CatalogResponseMapper catalogResponseMapper;

    @GetMapping("/contact-types")
    @Operation(summary = "Lista los tipos de contacto activos")
    public ResponseEntity<BaseResponse<List<ContactTypeResponse>>> getContactTypes() {
        List<ContactTypeResponse> data = catalogResponseMapper
                .toContactTypeResponses(findCatalogsUseCase.findContactTypes());
        return ResponseEntity.ok(BaseResponse.success(data, "Tipos de contacto obtenidos con éxito"));
    }

    @GetMapping("/genders")
    @Operation(summary = "Lista los géneros activos")
    public ResponseEntity<BaseResponse<List<GenderResponse>>> getGenders() {
        List<GenderResponse> data = catalogResponseMapper
                .toGenderResponses(findCatalogsUseCase.findGenders());
        return ResponseEntity.ok(BaseResponse.success(data, "Géneros obtenidos con éxito"));
    }

    @GetMapping("/document-types")
    @Operation(summary = "Lista los tipos de documento activos")
    public ResponseEntity<BaseResponse<List<DocumentTypeResponse>>> getDocumentTypes() {
        List<DocumentTypeResponse> data = catalogResponseMapper
                .toDocumentTypeResponses(findCatalogsUseCase.findDocumentTypes());
        return ResponseEntity.ok(BaseResponse.success(data, "Tipos de documento obtenidos con éxito"));
    }
}
