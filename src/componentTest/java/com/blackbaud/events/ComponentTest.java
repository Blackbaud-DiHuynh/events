package com.blackbaud.events;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.web.WebAppConfiguration;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SpringApplicationConfiguration(classes = {Events.class, TestConfig.class})
@WebAppConfiguration
@IntegrationTest({"server.port=10000", "management.port=10001"})
@ActiveProfiles({"local", "componentTest"})
@Sql(scripts = "classpath:/db/test_cleanup.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public @interface ComponentTest {

}
