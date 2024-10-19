package br.com.alexandre.BancoDeSangue.configuration.resources.getReceptorsByDonators;

import br.com.alexandre.BancoDeSangue.repositories.BancoDeSangueRepositoryImplement;
import br.com.alexandre.BancoDeSangue.useCase.getReceptorsByDonators.GetReceptorsByDonatorsUseCase;
import br.com.alexandre.BancoDeSangue.useCase.getReceptorsByDonators.impl.GetReceptorsByDonatorsUseCaseImpl;
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
