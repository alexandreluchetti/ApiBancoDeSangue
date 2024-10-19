package br.com.alexandre.BancoDeSangue.entrypoint.getObesityPercentagePerSex;

import br.com.alexandre.BancoDeSangue.useCase.getObesityPercentagePerSex.GetObesityPercentagePerSexUseCase;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/v1/bancodesangue", produces = MediaType.APPLICATION_JSON_VALUE)
public class GetObesityPercentagePerSexRestController {

    private final GetObesityPercentagePerSexUseCase useCase;

    public GetObesityPercentagePerSexRestController(GetObesityPercentagePerSexUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping(path = "/percentual/obesos/sexo")
    @Operation(summary = "Operacao para buscar o percentual de obesos por sexo")
    public Map<String, Double> percentageOverWeightPeopleBySex() {
        try {
            return useCase.getObesityPercentagePerSex();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }
}
