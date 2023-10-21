package br.com.alexandre.BancoDeSangue.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class SwaggerConfiguration {

    private final String name = "Banco de Sangue";
    private final String version = "1.0.0";
    private final String description = "Projeto de testes";

    @Bean
    public OpenAPI openAPIConfig() throws UnknownHostException {
        Contact contact = new Contact();
        contact.setEmail("luchetti.92@gmail.com");
        contact.setName("Banco de Sangue");

        String hostName = InetAddress.getLocalHost().getHostName();

        Info info = new Info()
                .title(name)
                .version(version)
                .contact(contact)
                .description(description + "\n\nHost: " + hostName);

        return new OpenAPI().info(info);

    }

//    @Bean
//    public Docket docket() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }

}
