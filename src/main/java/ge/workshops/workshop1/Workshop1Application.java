package ge.workshops.workshop1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication
public class  Workshop1Application {

    public static void main(String[] args) {
        SpringApplication.run(Workshop1Application.class, args);
    }

}
