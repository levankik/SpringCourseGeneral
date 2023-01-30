package ge.workshops.workshop1;

import ge.workshops.workshop1.config.JSONPlaceholderProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot Tutorial",
                version = "v1",
                description = "Spring Boot Tutorial API"
        )
)

@EnableScheduling
@EnableAsync

@EnableConfigurationProperties({
        JSONPlaceholderProperties.class
})

@SpringBootApplication
public class  Workshop1Application {
    public  static void main(String[] args) {
        SpringApplication.run(Workshop1Application.class, args);
    }
}
