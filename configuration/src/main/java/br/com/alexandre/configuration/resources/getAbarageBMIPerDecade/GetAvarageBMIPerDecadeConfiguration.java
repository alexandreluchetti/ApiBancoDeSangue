package br.com.alexandre.configuration.resources.getAbarageBMIPerDecade;

import br.com.alexandre.BancoDeSangueRepositoryImplement;
import br.com.alexandre.useCase.getAbarageBMIPerDecade.GetAvarageBMIPerDecadeUseCase;
import br.com.alexandre.useCase.getAbarageBMIPerDecade.impl.GetAvarageBMIPerDecadeUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetAvarageBMIPerDecadeConfiguration {

    @Bean
    public GetAvarageBMIPerDecadeUseCase loadGetAvarageBMIPerDecadeUseCase(
            BancoDeSangueRepositoryImplement repository
    ) {
        return new GetAvarageBMIPerDecadeUseCaseImpl(repository);
    }
}
