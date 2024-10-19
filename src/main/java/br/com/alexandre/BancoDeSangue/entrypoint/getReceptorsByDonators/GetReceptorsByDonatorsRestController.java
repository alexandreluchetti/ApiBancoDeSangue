package br.com.alexandre.BancoDeSangue.entrypoint.getReceptorsByDonators;

import br.com.alexandre.BancoDeSangue.core.useCase.getReceptorsByDonators.GetReceptorsByDonatorsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/v1/bancodesangue", produces = MediaType.APPLICATION_JSON_VALUE)
public class GetReceptorsByDonatorsRestController {

    private final GetReceptorsByDonatorsUseCase useCase;

    public GetReceptorsByDonatorsRestController(GetReceptorsByDonatorsUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping(path = "/quantidade/receptores/tiposanguineo/doador")
    @Operation(summary = "Operacao para buscar a quantidade de receptores para cada tipo sanguineo doador")
    public Map<String, Integer> amountOfRecipientsForEachBloodTypeDonor() {
        try {
            return useCase.amountOfRecipientsForEachBloodTypeDonor();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }
}
