package by.kuropatin.javafx.migration;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@RequiredArgsConstructor
public class MigrationHandler {

    private final DataSource dataSource;

    public void migrate() {
        Flyway.configure()
                .dataSource(dataSource)
                .locations("db/migration")
                .validateOnMigrate(true)
                .validateMigrationNaming(true)
                .schemas("public")
                .defaultSchema("public")
                .load()
                .migrate();
    }
}