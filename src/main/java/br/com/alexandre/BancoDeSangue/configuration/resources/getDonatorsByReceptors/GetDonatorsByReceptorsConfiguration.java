package br.com.alexandre.BancoDeSangue.configuration.resources.getDonatorsByReceptors;

import br.com.alexandre.BancoDeSangue.repositories.BancoDeSangueRepositoryImplement;
import br.com.alexandre.BancoDeSangue.useCase.getDonatorsByReceptors.GetDonatorsByReceptorsUseCase;
import br.com.alexandre.BancoDeSangue.useCase.getDonatorsByReceptors.impl.GetDonatorsByReceptorsUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetDonatorsByReceptorsConfiguration {

    @Bean
    public GetDonatorsByReceptorsUseCase loadGetDonatorsByReceptorsUseCase(
            BancoDeSangueRepositoryImplement repository
    ) {
        return new GetDonatorsByReceptorsUseCaseImpl(repository);
    }
}
