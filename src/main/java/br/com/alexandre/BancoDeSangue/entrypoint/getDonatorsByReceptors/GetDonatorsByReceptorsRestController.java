package br.com.alexandre.BancoDeSangue.entrypoint.getDonatorsByReceptors;

import br.com.alexandre.BancoDeSangue.core.useCase.getDonatorsByReceptors.GetDonatorsByReceptorsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/v1/bancodesangue", produces = MediaType.APPLICATION_JSON_VALUE)
public class GetDonatorsByReceptorsRestController {

    private final GetDonatorsByReceptorsUseCase useCase;

    public GetDonatorsByReceptorsRestController(GetDonatorsByReceptorsUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping(path = "/quantidade/doadores/tiposanguineo/receptor")
    @Operation(summary = "Operacao para buscar a quantidade de doadores para cada tipo sanguineo receptor")
    public Map<String, Integer> amountOfDonorsForEachBloodTypeRecipient() {
        try {
            return useCase.amountOfDonorsForEachBloodTypeRecipient();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

}
