package hu.ponte.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

public class UserServiceApplication {

    @EnableWebSecurity
    @SpringBootApplication
    public class CalculatorServiceApplication
    {
        public static void main(String[] args)
        {
            SpringApplication.run(UserServiceApplication.class, args);
        }
    }
}
