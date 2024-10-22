package br.com.alexandre.BancoDeSangue.entrypoint.getReceptorsByDonators;

import br.com.alexandre.BancoDeSangue.core.useCase.getReceptorsByDonators.GetReceptorsByDonatorsUseCase;
import br.com.alexandre.BancoDeSangue.entrypoint.getReceptorsByDonators.dto.ReceptorsByDonatorsResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "Banco de Sangue")
@RestController
@RequestMapping(value = "/v1/bancodesangue", produces = MediaType.APPLICATION_JSON_VALUE)
public class GetReceptorsByDonatorsRestController {

    private final GetReceptorsByDonatorsUseCase useCase;

    public GetReceptorsByDonatorsRestController(GetReceptorsByDonatorsUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping(path = "/quantidade/receptores/tiposanguineo/doador")
    @Operation(summary = "Operacao para buscar a quantidade de receptores para cada tipo sanguineo doador")
    public ResponseEntity<ReceptorsByDonatorsResponseDto> amountOfRecipientsForEachBloodTypeDonor() {
        return ResponseEntity.ok(new ReceptorsByDonatorsResponseDto(useCase.amountOfRecipientsForEachBloodTypeDonor()));
    }
}
