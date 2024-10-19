package br.com.alexandre.BancoDeSangue.entrypoint.getPeoplePerState;

import br.com.alexandre.BancoDeSangue.core.useCase.getPeoplePerState.GetPeoplePerStateUseCase;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/v1/bancodesangue", produces = MediaType.APPLICATION_JSON_VALUE)
public class GetPeoplePerStateRestController {

    private final GetPeoplePerStateUseCase useCase;

    public GetPeoplePerStateRestController(GetPeoplePerStateUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping(path = "/pessoas/estados")
    @Operation(summary = "Operacao para buscar a quantidade de candidatos em cada state do Brasil")
    public Map<String, Integer> candidatesPerState() {
        try {
            return useCase.getPeoplePerState();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }
}
