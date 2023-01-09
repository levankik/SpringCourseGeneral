package ge.workshops.workshop1.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties (prefix = "jsonplaceholder")
public class JSONPlaceholderProperties {
    private String host;
    private String username = "user";
    private String password;
    private boolean enabled = true;

}
