package br.com.alexandre.registerPerson.dto.registerPeople;

import br.com.alexandre.registerPeople.dto.PersonDto;
import br.com.alexandre.useCase.registerPeople.PeopleRegistrationUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@Tag(name = "Banco de Sangue")
@RestController
@RequestMapping(value = "/v1/bancodesangue", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonRegistrationRestController {

    private static final String PEOPLE_PATH = "/pessoa";
    private static final Logger logger = LoggerFactory.getLogger(PersonRegistrationRestController.class);

    private final PeopleRegistrationUseCase useCase;

    public PersonRegistrationRestController(PeopleRegistrationUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping(path = PEOPLE_PATH)
    @Operation(summary = "Operacao para enviar uma lista de pessoas para o banco de dados")
    public ResponseEntity<List<PersonDto>> registerPeople(@RequestBody PersonDto personDto) {
        logger.info(PEOPLE_PATH + " - REQUEST: " + personDto.toString());
        return ResponseEntity.ok(
                useCase.peopleRegistration(
                        Stream.of(personDto).map(PersonDto::toPerson).toList()
                )   .stream().map(PersonDto::fromPerson).toList()
        );
    }
}
