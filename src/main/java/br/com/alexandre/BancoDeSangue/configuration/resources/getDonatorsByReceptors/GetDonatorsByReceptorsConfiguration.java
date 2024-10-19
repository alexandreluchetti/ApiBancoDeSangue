package br.com.alexandre.BancoDeSangue.configuration.resources.getDonatorsByReceptors;

import br.com.alexandre.BancoDeSangue.dataprovider.BancoDeSangueRepositoryImplement;
import br.com.alexandre.BancoDeSangue.core.useCase.getDonatorsByReceptors.GetDonatorsByReceptorsUseCase;
import br.com.alexandre.BancoDeSangue.core.useCase.getDonatorsByReceptors.impl.GetDonatorsByReceptorsUseCaseImpl;
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
