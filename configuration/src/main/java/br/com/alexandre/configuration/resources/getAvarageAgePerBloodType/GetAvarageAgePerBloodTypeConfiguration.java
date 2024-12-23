package br.com.alexandre.configuration.resources.getAvarageAgePerBloodType;

import br.com.alexandre.BancoDeSangueRepositoryImplement;
import br.com.alexandre.useCase.getAvarageAgePerBloodType.GetAvarageAgePerBloodTypeUseCase;
import br.com.alexandre.useCase.getAvarageAgePerBloodType.impl.GetAvarageAgePerBloodTypeUseCaseImpl;
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
