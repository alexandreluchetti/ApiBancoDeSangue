package br.com.alexandre.BancoDeSangue.configuration.resources.getPeoplePerState;

import br.com.alexandre.BancoDeSangue.dataprovider.BancoDeSangueRepositoryImplement;
import br.com.alexandre.BancoDeSangue.core.useCase.getPeoplePerState.GetPeoplePerStateUseCase;
import br.com.alexandre.BancoDeSangue.core.useCase.getPeoplePerState.impl.GetPeoplePerStateUseCaseImpl;
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
