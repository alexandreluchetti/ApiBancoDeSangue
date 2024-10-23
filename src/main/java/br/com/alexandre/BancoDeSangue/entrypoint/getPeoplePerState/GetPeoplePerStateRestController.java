package br.com.alexandre.BancoDeSangue.entrypoint.getPeoplePerState;

import br.com.alexandre.BancoDeSangue.core.useCase.getPeoplePerState.GetPeoplePerStateUseCase;
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
public class GetPeoplePerStateRestController {

    private static final String PEOPLE_PER_STATE = "/pessoas/estados";
    private static final Logger logger = LoggerFactory.getLogger(GetPeoplePerStateRestController.class);

    private final GetPeoplePerStateUseCase useCase;

    public GetPeoplePerStateRestController(GetPeoplePerStateUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping(path = PEOPLE_PER_STATE)
    @Operation(summary = "Operacao para buscar a quantidade de candidatos em cada state do Brasil")
    public ResponseEntity<Map<String, Integer>> candidatesPerState() {
        logger.info(PEOPLE_PER_STATE + " - REQUEST");
        Map<String, Integer> peoplePerState = useCase.getPeoplePerState();
        logger.info(PEOPLE_PER_STATE + " - RESPONSE: {}", peoplePerState.toString());
        return ResponseEntity.ok(peoplePerState);
    }
}
