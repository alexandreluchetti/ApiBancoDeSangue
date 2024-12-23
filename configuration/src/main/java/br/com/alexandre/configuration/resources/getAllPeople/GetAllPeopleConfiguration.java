package br.com.alexandre.configuration.resources.getAllPeople;

import br.com.alexandre.BancoDeSangueRepositoryImplement;
import br.com.alexandre.useCase.getAllPeople.GetAllPeopleUseCase;
import br.com.alexandre.useCase.getAllPeople.impl.GetAllPeopleUseCaseImpl;
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
