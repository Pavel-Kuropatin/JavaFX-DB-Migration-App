package by.kuropatin.javafx.controller;

import by.kuropatin.javafx.migration.MigrationHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Controller {

    @FXML
    private Label label;

    private final MigrationHandler migrationHandler;

    public void migrate() {
        log.info("Migration started");
        try {
            final String migrationResult = migrationHandler.migrate();
            label.setText(migrationResult);
            log.info(migrationResult);
        } catch (Exception e) {
            label.setText("Migration ended with error: " + e.getMessage());
            log.info("Migration ended with error: " + e.getMessage());
        }
    }
}