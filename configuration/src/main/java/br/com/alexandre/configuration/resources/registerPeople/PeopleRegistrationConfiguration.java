package br.com.alexandre.configuration.resources.registerPeople;

import br.com.alexandre.BancoDeSangueRepositoryImplement;
import br.com.alexandre.useCase.registerPeople.PeopleRegistrationUseCase;
import br.com.alexandre.useCase.registerPeople.impl.PeopleRegistrationUseCaseImpl;
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
