package br.com.alexandre.BancoDeSangue.entrypoint.getAvarageBMIPerDecade;

import br.com.alexandre.BancoDeSangue.core.useCase.getAbarageBMIPerDecade.GetAvarageBMIPerDecadeUseCase;
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
public class GetAvarageBMIPerDecadeRestController {

    private static final String AVARAGE_BMI_PER_DECADE = "/media/imc/decada";
    private static final Logger logger = LoggerFactory.getLogger(GetAvarageBMIPerDecadeRestController.class);

    private final GetAvarageBMIPerDecadeUseCase useCase;

    public GetAvarageBMIPerDecadeRestController(GetAvarageBMIPerDecadeUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping(path = AVARAGE_BMI_PER_DECADE)
    @Operation(summary = "Operacao para buscar a media de IMC por cada decada")
    public ResponseEntity<Map<String, Double>> averageBmiPerDecade() {
        logger.info(AVARAGE_BMI_PER_DECADE + " - REQUEST");
        Map<String, Double> avarageBMIPerDacade = useCase.getAvarageBMIPerDacade();
        logger.info(AVARAGE_BMI_PER_DECADE + " - RESPONSE: {}", avarageBMIPerDacade.toString());
        return ResponseEntity.ok(avarageBMIPerDacade);
    }
}
