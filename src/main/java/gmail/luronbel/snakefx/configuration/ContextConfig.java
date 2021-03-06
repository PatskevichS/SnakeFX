package gmail.luronbel.snakefx.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "gmail.luronbel.snakefx")
@PropertySource(name = "myProperties", value = "application.properties")
public class ContextConfig {
}
