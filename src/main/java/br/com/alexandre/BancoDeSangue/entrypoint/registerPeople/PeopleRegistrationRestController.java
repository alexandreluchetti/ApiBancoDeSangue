package br.com.alexandre.BancoDeSangue.entrypoint.registerPeople;

import br.com.alexandre.BancoDeSangue.core.entities.Person;
import br.com.alexandre.BancoDeSangue.entrypoint.registerPeople.dto.PersonDto;
import br.com.alexandre.BancoDeSangue.configuration.exceptions.EmptyListException;
import br.com.alexandre.BancoDeSangue.core.useCase.registerPeople.PeopleRegistrationUseCase;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/bancodesangue", produces = MediaType.APPLICATION_JSON_VALUE)
public class PeopleRegistrationRestController {

    private final PeopleRegistrationUseCase useCase;

    public PeopleRegistrationRestController(PeopleRegistrationUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping(path = "/pessoas")///envia/pessoas
    @Operation(summary = "Operacao para enviar uma lista de pessoas para o banco de dados")
    public ResponseEntity<List<PersonDto>> registerPeople(@RequestBody List<PersonDto> personDtos) {
        if (personDtos == null || personDtos.isEmpty()) {
            throw EmptyListException.noPersonAdded();
        }

        return ResponseEntity.ok(
                useCase.peopleRegistration(
                        personDtos.stream().map(PersonDto::toPerson).toList()
                )   .stream().map(Person::toDto).toList()
        );
    }
}
