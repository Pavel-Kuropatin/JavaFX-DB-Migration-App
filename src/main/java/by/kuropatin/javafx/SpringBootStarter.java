package by.kuropatin.javafx;

import by.kuropatin.javafx.controller.JavaFxApplication;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootStarter {

    public static void main(String[] args) {
        Application.launch(JavaFxApplication.class);
//        SpringApplication.run(SpringBootStarter.class, args);
    }
}