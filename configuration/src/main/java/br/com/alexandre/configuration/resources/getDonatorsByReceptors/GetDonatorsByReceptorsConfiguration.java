package br.com.alexandre.configuration.resources.getDonatorsByReceptors;

import br.com.alexandre.BancoDeSangueRepositoryImplement;
import br.com.alexandre.useCase.getDonatorsByReceptors.GetDonatorsByReceptorsUseCase;
import br.com.alexandre.useCase.getDonatorsByReceptors.impl.GetDonatorsByReceptorsUseCaseImpl;
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
