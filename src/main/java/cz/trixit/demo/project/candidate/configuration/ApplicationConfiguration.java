package cz.trixit.demo.project.candidate.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("cz.trixit.demo.project.candidate.dao")
@EntityScan("cz.trixit.demo.project.candidate.model")
public class ApplicationConfiguration {

}
