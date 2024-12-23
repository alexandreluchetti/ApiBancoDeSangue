package br.com.alexandre.configuration.resources.repository;

import br.com.alexandre.BancoDeSangueJpaRepository;
import br.com.alexandre.BancoDeSangueRepositoryImplement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BancoDeSangueRepositoryConfiguration {

    @Bean
    public BancoDeSangueRepositoryImplement loadBancoDeSangueRepositoryImplement(
            BancoDeSangueJpaRepository repository
    ) {
        return new BancoDeSangueRepositoryImplement(repository);
    }
}
