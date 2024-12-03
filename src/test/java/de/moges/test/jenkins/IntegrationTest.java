package de.moges.test.jenkins;

import de.moges.test.jenkins.config.AsyncSyncConfiguration;
import de.moges.test.jenkins.config.EmbeddedSQL;
import de.moges.test.jenkins.config.JacksonConfiguration;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = { JenkinsTestApp.class, JacksonConfiguration.class, AsyncSyncConfiguration.class })
@EmbeddedSQL
public @interface IntegrationTest {
}
