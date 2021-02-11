package first;

import org.apache.log4j.Logger;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testcontainers.containers.PostgreSQLContainer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {
    private static final Logger userLogger = Logger.getLogger(AbstractIntegrationTest.class);

    private static final String TEST_DATABASE_NAME = "first";
    private static final String TEST_USER = "postgres";
    private static final String TEST_PASSWORD = "admin";

    @ClassRule
    public static PostgreSQLContainer postgres = new PostgreSQLContainer()
            .withDatabaseName(TEST_DATABASE_NAME)
            .withUsername(TEST_USER)
            .withPassword(TEST_PASSWORD);

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        private static final String TEST_DATASOURCE = postgres.getJdbcUrl();

        @Override
        public void initialize(final ConfigurableApplicationContext configurableApplicationContext) {
            userLogger.info("URL " + TEST_DATASOURCE);

            TestPropertyValues values = TestPropertyValues.of(
                    "spring.datasource.url=" + TEST_DATASOURCE,
                    "spring.datasource.username=" + TEST_USER,
                    "spring.datasource.password=" + TEST_PASSWORD
            );
            values.applyTo(configurableApplicationContext);
        }
    }
}
