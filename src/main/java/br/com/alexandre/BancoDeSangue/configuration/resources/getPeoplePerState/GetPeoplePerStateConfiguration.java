package br.com.alexandre.BancoDeSangue.configuration.resources.getPeoplePerState;

import br.com.alexandre.BancoDeSangue.repositories.BancoDeSangueRepositoryImplement;
import br.com.alexandre.BancoDeSangue.useCase.getPeoplePerState.GetPeoplePerStateUseCase;
import br.com.alexandre.BancoDeSangue.useCase.getPeoplePerState.impl.GetPeoplePerStateUseCaseImpl;
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
