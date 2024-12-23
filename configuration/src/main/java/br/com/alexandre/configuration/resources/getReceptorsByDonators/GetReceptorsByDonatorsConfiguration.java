package br.com.alexandre.configuration.resources.getReceptorsByDonators;

import br.com.alexandre.BancoDeSangueRepositoryImplement;
import br.com.alexandre.useCase.getReceptorsByDonators.GetReceptorsByDonatorsUseCase;
import br.com.alexandre.useCase.getReceptorsByDonators.impl.GetReceptorsByDonatorsUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetReceptorsByDonatorsConfiguration {

    @Bean
    public GetReceptorsByDonatorsUseCase loadGetReceptorsByDonatorsUseCase(
            BancoDeSangueRepositoryImplement repository
    ) {
        return new GetReceptorsByDonatorsUseCaseImpl(repository);
    }
}
