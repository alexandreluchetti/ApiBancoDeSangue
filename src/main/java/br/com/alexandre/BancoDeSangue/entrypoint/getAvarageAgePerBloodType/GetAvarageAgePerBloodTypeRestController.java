package br.com.alexandre.BancoDeSangue.entrypoint.getAvarageAgePerBloodType;

import br.com.alexandre.BancoDeSangue.core.useCase.getAvarageAgePerBloodType.GetAvarageAgePerBloodTypeUseCase;
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
public class GetAvarageAgePerBloodTypeRestController {

    private static final String AVARAGE_AGE_BY_BLOOD_TYPE = "/media/idade/tiposanguineo";
    private static final Logger logger = LoggerFactory.getLogger(GetAvarageAgePerBloodTypeRestController.class);

    private final GetAvarageAgePerBloodTypeUseCase useCase;

    public GetAvarageAgePerBloodTypeRestController(GetAvarageAgePerBloodTypeUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping(path = AVARAGE_AGE_BY_BLOOD_TYPE)
    @Operation(summary = "Operacao para buscar idade media por cada tipo sanguineo")
    public ResponseEntity<Map<String, Double>> avgAgeByBloodType() {
        logger.info(AVARAGE_AGE_BY_BLOOD_TYPE + " - REQUEST");
        Map<String, Double> avgAgeByBloodType = useCase.getAvgAgeByBloodType();
        logger.info(AVARAGE_AGE_BY_BLOOD_TYPE + " - RESPONSE: {}", avgAgeByBloodType.toString());
        return ResponseEntity.ok(avgAgeByBloodType);
    }
}
