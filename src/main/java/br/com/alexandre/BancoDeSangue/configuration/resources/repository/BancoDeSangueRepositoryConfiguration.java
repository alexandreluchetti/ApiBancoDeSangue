package br.com.alexandre.BancoDeSangue.configuration.resources.repository;

import br.com.alexandre.BancoDeSangue.dataprovider.BancoDeSangueRepository;
import br.com.alexandre.BancoDeSangue.dataprovider.BancoDeSangueRepositoryImplement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BancoDeSangueRepositoryConfiguration {

    @Bean
    public BancoDeSangueRepositoryImplement loadBancoDeSangueRepositoryImplement(
            BancoDeSangueRepository repository
    ) {
        return new BancoDeSangueRepositoryImplement(repository);
    }
}
