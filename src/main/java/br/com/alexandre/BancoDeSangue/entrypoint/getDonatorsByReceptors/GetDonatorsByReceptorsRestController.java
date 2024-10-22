package br.com.alexandre.BancoDeSangue.entrypoint.getDonatorsByReceptors;

import br.com.alexandre.BancoDeSangue.core.useCase.getDonatorsByReceptors.GetDonatorsByReceptorsUseCase;
import br.com.alexandre.BancoDeSangue.entrypoint.getDonatorsByReceptors.dto.DonatorsByReceptorsResponseDto;
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
public class GetDonatorsByReceptorsRestController {

    private final GetDonatorsByReceptorsUseCase useCase;

    public GetDonatorsByReceptorsRestController(GetDonatorsByReceptorsUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping(path = "/quantidade/doadores/tiposanguineo/receptor")
    @Operation(summary = "Operacao para buscar a quantidade de doadores para cada tipo sanguineo receptor")
    public ResponseEntity<DonatorsByReceptorsResponseDto> amountOfDonorsForEachBloodTypeRecipient() {
        return ResponseEntity.ok(new DonatorsByReceptorsResponseDto(useCase.amountOfDonorsForEachBloodTypeRecipient()));
    }

}
