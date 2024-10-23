package br.com.alexandre.BancoDeSangue.entrypoint.getReceptorsByDonators;

import br.com.alexandre.BancoDeSangue.core.useCase.getReceptorsByDonators.GetReceptorsByDonatorsUseCase;
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
public class GetReceptorsByDonatorsRestController {

    private static final String RECEPTORS_BY_DONATORS = "/quantidade/receptores/tiposanguineo/doador";
    private static final Logger logger = LoggerFactory.getLogger(GetReceptorsByDonatorsRestController.class);

    private final GetReceptorsByDonatorsUseCase useCase;

    public GetReceptorsByDonatorsRestController(GetReceptorsByDonatorsUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping(path = RECEPTORS_BY_DONATORS)
    @Operation(summary = "Operacao para buscar a quantidade de receptores para cada tipo sanguineo doador")
    public ResponseEntity<Map<String, Integer>> amountOfRecipientsForEachBloodTypeDonor() {
        logger.info(RECEPTORS_BY_DONATORS + " - REQUEST");
        Map<String, Integer> stringIntegerMap = useCase.amountOfRecipientsForEachBloodTypeDonor();
        logger.info(RECEPTORS_BY_DONATORS + " - RESPONSE: {}", stringIntegerMap.toString());
        return ResponseEntity.ok(stringIntegerMap);
    }
}
