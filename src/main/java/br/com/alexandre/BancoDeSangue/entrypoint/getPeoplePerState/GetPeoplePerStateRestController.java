package br.com.alexandre.BancoDeSangue.entrypoint.getPeoplePerState;

import br.com.alexandre.BancoDeSangue.core.useCase.getPeoplePerState.GetPeoplePerStateUseCase;
import br.com.alexandre.BancoDeSangue.entrypoint.getPeoplePerState.dto.PeoplePerStateResponseDto;
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
public class GetPeoplePerStateRestController {

    private final GetPeoplePerStateUseCase useCase;

    public GetPeoplePerStateRestController(GetPeoplePerStateUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping(path = "/pessoas/estados")
    @Operation(summary = "Operacao para buscar a quantidade de candidatos em cada state do Brasil")
    public ResponseEntity<PeoplePerStateResponseDto> candidatesPerState() {
        return ResponseEntity.ok(new PeoplePerStateResponseDto(useCase.getPeoplePerState()));
    }
}
