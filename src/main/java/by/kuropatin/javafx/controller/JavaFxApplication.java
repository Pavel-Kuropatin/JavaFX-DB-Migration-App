package by.kuropatin.javafx.controller;

import by.kuropatin.javafx.SpringBootStarter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFxApplication extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        context = new SpringApplicationBuilder(SpringBootStarter.class).run();
    }

    @Override
    public void start(final Stage stage) throws Exception {
        context.publishEvent(new StageReadyEvent(stage));

        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        final Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        final Image icon = new Image("icon.png");

        stage.getIcons().add(icon);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
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