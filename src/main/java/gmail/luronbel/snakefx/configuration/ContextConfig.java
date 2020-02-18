package gmail.luronbel.snakefx.configuration;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

@Configuration
@ComponentScan
@PropertySource(name = "myProperties", value = "application.properties")
public class ContextConfig {
    public static final String ROOT_BEAN = "root";
    public static final String MAIN_SCENE = "mainScene";

    @Bean(name = ROOT_BEAN)
    public Parent root() throws IOException {
        return FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
    }

    @Bean(name = MAIN_SCENE)
    public Scene mainScene(@Autowired @Qualifier(ROOT_BEAN) Parent root) {
        return new Scene(root, 300, 275);
    }
}
