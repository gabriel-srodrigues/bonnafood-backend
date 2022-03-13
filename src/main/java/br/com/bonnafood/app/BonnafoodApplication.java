package br.com.bonnafood.app;

import  br.com.bonnafood.app.common.jpa.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class BonnafoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(BonnafoodApplication.class, args);
    }

}
