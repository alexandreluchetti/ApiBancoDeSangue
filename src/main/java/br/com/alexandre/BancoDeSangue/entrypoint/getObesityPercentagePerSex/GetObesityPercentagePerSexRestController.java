package br.com.alexandre.BancoDeSangue.entrypoint.getObesityPercentagePerSex;

import br.com.alexandre.BancoDeSangue.core.useCase.getObesityPercentagePerSex.GetObesityPercentagePerSexUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "Banco de Sangue")
@RestController
@RequestMapping(value = "/v1/bancodesangue", produces = MediaType.APPLICATION_JSON_VALUE)
public class GetObesityPercentagePerSexRestController {

    private static final String OBESITY_PERCENTAGE_BY_SEX = "/percentual/obesos/sexo";
    private static final Logger logger = LoggerFactory.getLogger(GetObesityPercentagePerSexRestController.class);

    private final GetObesityPercentagePerSexUseCase useCase;

    public GetObesityPercentagePerSexRestController(GetObesityPercentagePerSexUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping(path = OBESITY_PERCENTAGE_BY_SEX)
    @Operation(summary = "Operacao para buscar o percentual de obesos por sexo")
    public ResponseEntity<Map<String, Double>> percentageOverWeightPeopleBySex() {
        logger.info(OBESITY_PERCENTAGE_BY_SEX + " - REQUEST");
        Map<String, Double> obesityPercentagePerSex = useCase.getObesityPercentagePerSex();
        logger.info(OBESITY_PERCENTAGE_BY_SEX + " - RESPONSE: {}", obesityPercentagePerSex.toString());
        return ResponseEntity.ok(obesityPercentagePerSex);
    }
}
