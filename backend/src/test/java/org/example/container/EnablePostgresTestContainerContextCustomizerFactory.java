package org.example.container;

import lombok.EqualsAndHashCode;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfigurationAttributes;
import org.springframework.test.context.ContextCustomizer;
import org.springframework.test.context.ContextCustomizerFactory;
import org.springframework.test.context.MergedContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

import java.lang.annotation.*;
import java.util.List;
import java.util.Map;

public class EnablePostgresTestContainerContextCustomizerFactory implements ContextCustomizerFactory {
    @Override
    public ContextCustomizer createContextCustomizer(Class<?> testClass, List<ContextConfigurationAttributes> configAttributes) {
        if (!(AnnotatedElementUtils.hasAnnotation(testClass, EnabledPostgresTestContainer.class))) {
            return null;
        }
        return new PostgresTestContainerContextCustomizer();
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Inherited
    public @interface EnabledPostgresTestContainer {
    }

    @EqualsAndHashCode
    private static class PostgresTestContainerContextCustomizer implements ContextCustomizer {

        @Override
        public void customizeContext(ConfigurableApplicationContext context, MergedContextConfiguration mergedConfig) {
            var postgresContainer = new PostgreSQLContainer<>("postgres:11-bullseye");

            postgresContainer.start();
            var properties = Map.<String, Object>of(
                    "spring.datasource.url", postgresContainer.getJdbcUrl(),
                    "spring.datasource.username", postgresContainer.getUsername(),
                    "spring.datasource.password", postgresContainer.getPassword(),
                    // Prevent any in memory db from replacing the data source
                    // See @AutoConfigureTestDatabase
                    "spring.test.database.replace", "NONE"
            );
            var propertySource = new MapPropertySource("PostgresContainer Test Properties", properties);
            context.getEnvironment().getPropertySources().addFirst(propertySource);
        }
    }
}
