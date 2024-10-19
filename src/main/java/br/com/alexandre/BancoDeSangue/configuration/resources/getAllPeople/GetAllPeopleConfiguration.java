package br.com.alexandre.BancoDeSangue.configuration.resources.getAllPeople;

import br.com.alexandre.BancoDeSangue.repositories.BancoDeSangueRepositoryImplement;
import br.com.alexandre.BancoDeSangue.useCase.registerPeople.getAllPeople.GetAllPeopleUseCase;
import br.com.alexandre.BancoDeSangue.useCase.registerPeople.getAllPeople.impl.GetAllPeopleUseCaseImpl;
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
