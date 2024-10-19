package br.com.alexandre.BancoDeSangue.configuration.resources.getAllPeople;

import br.com.alexandre.BancoDeSangue.dataprovider.BancoDeSangueRepositoryImplement;
import br.com.alexandre.BancoDeSangue.core.useCase.getAllPeople.GetAllPeopleUseCase;
import br.com.alexandre.BancoDeSangue.core.useCase.getAllPeople.impl.GetAllPeopleUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetAllPeopleConfiguration {

    @Bean
    public GetAllPeopleUseCase loadGetAllPeopleUseCase(
            BancoDeSangueRepositoryImplement repository
    ) {
        return new GetAllPeopleUseCaseImpl(repository);
    }
}
