package first;

import first.repository.UsersRepository;
import org.apache.log4j.Logger;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(initializers = { FourthControllerTest.Initializer.class})
public class FourthControllerTest {
    private static final Logger userLogger = Logger.getLogger(FourthControllerTest.class);

    @Autowired
    private UsersRepository usersRepository;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11-alpine")
            .withDatabaseName("fourthdb")
            .withUsername("postgres")
            .withPassword("admin");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            postgreSQLContainer.start();
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword(),
                    "spring.jpa.hibernate.ddl-auto=create-drop"
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Test
    public void save_and_read_user(){
        long l = 1;
        User user = new User(l, "first", 17);
        usersRepository.save(user);

        List<User> allUsers = usersRepository.findAll();
        userLogger.info("size: " + allUsers.size());
        userLogger.info("user0: " + allUsers.get(0));

        //assertThat(allBooks).isNotEmpty();
        //assertThat(allBooks).hasSize(1);
        //assertThat(allBooks.get(0).getTitle()).isEqualTo("My fancy title");
    }
}
