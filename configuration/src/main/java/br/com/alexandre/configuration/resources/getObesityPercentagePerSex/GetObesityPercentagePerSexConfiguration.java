package br.com.alexandre.configuration.resources.getObesityPercentagePerSex;

import br.com.alexandre.BancoDeSangueRepositoryImplement;
import br.com.alexandre.useCase.getObesityPercentagePerSex.GetObesityPercentagePerSexUseCase;
import br.com.alexandre.useCase.getObesityPercentagePerSex.impl.GetObesityPercentagePerSexUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetObesityPercentagePerSexConfiguration {

    @Bean
    public GetObesityPercentagePerSexUseCase loadGetObesityPercentagePerSexUseCase(
            BancoDeSangueRepositoryImplement repository
    ) {
        return new GetObesityPercentagePerSexUseCaseImpl(repository);
    }
}
