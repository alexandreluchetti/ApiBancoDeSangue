package br.com.alexandre.BancoDeSangue.configuration.resources.registerPeople;

import br.com.alexandre.BancoDeSangue.repositories.BancoDeSangueRepositoryImplement;
import br.com.alexandre.BancoDeSangue.useCase.registerPeople.PeopleRegistrationUseCase;
import br.com.alexandre.BancoDeSangue.useCase.registerPeople.impl.PeopleRegistrationUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PeopleRegistrationConfiguration {

    @Bean
    public PeopleRegistrationUseCase loadPeopleRegistrationUseCase(
        BancoDeSangueRepositoryImplement repository
    ) {
        return new PeopleRegistrationUseCaseImpl(repository);
    }

}
