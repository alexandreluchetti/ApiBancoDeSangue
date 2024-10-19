package br.com.alexandre.BancoDeSangue.configuration.resources.getAbarageBMIPerDecade;

import br.com.alexandre.BancoDeSangue.dataprovider.BancoDeSangueRepositoryImplement;
import br.com.alexandre.BancoDeSangue.core.useCase.getAbarageBMIPerDecade.GetAvarageBMIPerDecadeUseCase;
import br.com.alexandre.BancoDeSangue.core.useCase.getAbarageBMIPerDecade.impl.GetAvarageBMIPerDecadeUseCaseImpl;
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
