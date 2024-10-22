package br.com.alexandre.BancoDeSangue.entrypoint.getAllPeople;

import br.com.alexandre.BancoDeSangue.core.entities.Person;
import br.com.alexandre.BancoDeSangue.entrypoint.registerPeople.dto.PersonDto;
import br.com.alexandre.BancoDeSangue.core.useCase.getAllPeople.GetAllPeopleUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Banco de Sangue")
@RestController
@RequestMapping(value = "/v1/bancodesangue", produces = MediaType.APPLICATION_JSON_VALUE)
public class GetAllPeopleRestController {

    private final GetAllPeopleUseCase useCase;

    public GetAllPeopleRestController(GetAllPeopleUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping(path = "/pessoas")
    @Operation(summary = "Operacao para buscar uma lista de pessoas no banco de dados")
    public List<PersonDto> getPeople() {
        try {
            return useCase.getPersonList().stream().map(Person::toDto).toList();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }
}
