package br.com.alexandre.BancoDeSangue.configuration.resources.getAvarageAgePerBloodType;

import br.com.alexandre.BancoDeSangue.repositories.BancoDeSangueRepositoryImplement;
import br.com.alexandre.BancoDeSangue.useCase.getAvarageAgePerBloodType.GetAvarageAgePerBloodTypeUseCase;
import br.com.alexandre.BancoDeSangue.useCase.getAvarageAgePerBloodType.impl.GetAvarageAgePerBloodTypeUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetAvarageAgePerBloodTypeConfiguration {

    @Bean
    public GetAvarageAgePerBloodTypeUseCase loadGetAvarageAgePerBloodTypeUseCase(
            BancoDeSangueRepositoryImplement repository
    ) {
        return new GetAvarageAgePerBloodTypeUseCaseImpl(repository);
    }
}
