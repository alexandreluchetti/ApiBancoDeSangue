package br.com.alexandre.BancoDeSangue.entrypoint.getAllPeople;

import br.com.alexandre.BancoDeSangue.core.entities.Person;
import br.com.alexandre.BancoDeSangue.entrypoint.registerPeople.dto.PersonDto;
import br.com.alexandre.BancoDeSangue.core.useCase.getAllPeople.GetAllPeopleUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Banco de Sangue")
@RestController
@RequestMapping(value = "/v1/bancodesangue", produces = MediaType.APPLICATION_JSON_VALUE)
public class GetAllPeopleRestController {

    private static final String PEOPLE_PATH = "/pessoas";
    private static final Logger logger = LoggerFactory.getLogger(GetAllPeopleRestController.class);

    private final GetAllPeopleUseCase useCase;

    public GetAllPeopleRestController(GetAllPeopleUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping(path = PEOPLE_PATH)
    @Operation(summary = "Operacao para buscar uma lista de pessoas no banco de dados")
    public ResponseEntity<List<PersonDto>> getPeople() {
        logger.info(PEOPLE_PATH + " - REQUEST");
        List<PersonDto> list = useCase.getPersonList().stream().map(Person::toDto).toList();
        logger.info(PEOPLE_PATH + " - RESPONSE: {}", list.size());
        return ResponseEntity.ok(list);
    }
}
