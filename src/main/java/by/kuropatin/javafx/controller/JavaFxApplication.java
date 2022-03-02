package by.kuropatin.javafx.controller;

import by.kuropatin.javafx.SpringBootStarter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class JavaFxApplication extends Application {

    private ConfigurableApplicationContext context;
    private Parent root;

    @Override
    public void init() throws IOException {
        context = SpringApplication.run(SpringBootStarter.class);
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        fxmlLoader.setControllerFactory(context::getBean);
        root = fxmlLoader.load();
    }

    @Override
    public void start(final Stage stage) {
        context.publishEvent(new StageReadyEvent(stage));

        final Scene scene = new Scene(root, 600, 400);
        final Image icon = new Image("icon.png");

        stage.getIcons().add(icon);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    @Override
    public void stop() {
        context.close();
        Platform.exit();
    }

    static class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage stage) {
            super(stage);
        }
    }
}