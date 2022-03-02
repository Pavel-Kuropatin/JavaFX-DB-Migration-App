package by.kuropatin.javafx.migration;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.output.MigrateResult;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Component
@RequiredArgsConstructor
public class MigrationHandler {

    private final DataSource dataSource;

    public String migrate() {
        final MigrateResult result = Flyway.configure()
                .dataSource(dataSource)
                .locations("db/migration")
                .validateOnMigrate(true)
                .validateMigrationNaming(true)
                .schemas("public")
                .defaultSchema("public")
                .load()
                .migrate();

        final String output;

        if (result.migrationsExecuted == 0) {
            output = "Database is up to date, no migrations needed";
        } else {
            final StringJoiner joiner = new StringJoiner(System.lineSeparator());
            joiner.add("Database name: " + result.database);
            joiner.add("Initial Schema Version: " + result.initialSchemaVersion);
            joiner.add("Target Schema Version: " + result.targetSchemaVersion);
            joiner.add("Migration count: " + result.migrationsExecuted);
            final List<String> names = new ArrayList<>();
            result.migrations.forEach(m -> names.add(m.version + "_" + m.description + "." + m.type));
            joiner.add("Migration names: " + names);
            joiner.add("Success: " + result.success);
            output = joiner.toString();
        }

        return output;
    }
}