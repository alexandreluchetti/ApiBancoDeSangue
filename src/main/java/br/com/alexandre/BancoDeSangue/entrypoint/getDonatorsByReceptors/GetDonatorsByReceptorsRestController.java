package br.com.alexandre.BancoDeSangue.entrypoint.getDonatorsByReceptors;

import br.com.alexandre.BancoDeSangue.core.useCase.getDonatorsByReceptors.GetDonatorsByReceptorsUseCase;
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
public class GetDonatorsByReceptorsRestController {

    private static final String DONATORS_BY_RECEPTORS = "/quantidade/doadores/tiposanguineo/receptor";
    private static final Logger logger = LoggerFactory.getLogger(GetDonatorsByReceptorsRestController.class);

    private final GetDonatorsByReceptorsUseCase useCase;

    public GetDonatorsByReceptorsRestController(GetDonatorsByReceptorsUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping(path = DONATORS_BY_RECEPTORS)
    @Operation(summary = "Operacao para buscar a quantidade de doadores para cada tipo sanguineo receptor")
    public ResponseEntity<Map<String, Integer>> amountOfDonorsForEachBloodTypeRecipient() {
        logger.info(DONATORS_BY_RECEPTORS + " - REQUEST");
        Map<String, Integer> stringIntegerMap = useCase.amountOfDonorsForEachBloodTypeRecipient();
        logger.info(DONATORS_BY_RECEPTORS + " - RESPONSE: {}", stringIntegerMap.toString());
        return ResponseEntity.ok(stringIntegerMap);
    }

}
