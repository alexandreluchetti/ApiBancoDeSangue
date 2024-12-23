package br.com.alexandre.configuration.resources.getPeoplePerState;

import br.com.alexandre.BancoDeSangueRepositoryImplement;
import br.com.alexandre.useCase.getPeoplePerState.GetPeoplePerStateUseCase;
import br.com.alexandre.useCase.getPeoplePerState.impl.GetPeoplePerStateUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetPeoplePerStateConfiguration {

    @Bean
    public GetPeoplePerStateUseCase loadGetPeoplePerStateUseCase(
            BancoDeSangueRepositoryImplement repository
    ) {
        return new GetPeoplePerStateUseCaseImpl(repository);
    }
}
