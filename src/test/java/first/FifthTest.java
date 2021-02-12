package first;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.stream.Stream;



public class FifthTest {
    private static final Logger userLogger = Logger.getLogger(FifthTest.class);
    private static final DockerImageName DEFAULT_IMAGE_NAME = DockerImageName.parse("mockserver/mockserver");

    private static final String TEST_DATABASE_NAME = "fifthdb";
    private static final String TEST_USER = "postgres";
    private static final String TEST_PASSWORD = "admin";

    private static Network network = Network.newNetwork();

    public static PostgreSQLContainer postgres = new PostgreSQLContainer()
            .withDatabaseName(TEST_DATABASE_NAME)
            .withUsername(TEST_USER)
            .withPassword(TEST_PASSWORD);

    /*public static PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>("debezium/postgres:11")
                    .withNetwork(network)
                    .withNetworkAliases("postgres");*/

    public static MockServerContainer mockServer = new MockServerContainer(DEFAULT_IMAGE_NAME)
            .withNetwork(network);

    @BeforeClass
    public static void startContainers() {
        Startables.deepStart(Stream.of(
                mockServer, postgres))
                .join();
    }

    @Test
    public void fifthTest() throws Exception {
        userLogger.info("SOME LOGS " + mockServer.getServerPort());
        userLogger.info("SOME LOGS 2 " + postgres.getContainerName());
        userLogger.info("Network " + network);
    }

    private Connection getConnection(
            PostgreSQLContainer<?> postgresContainer)
            throws SQLException {

        return DriverManager.getConnection(postgresContainer.getJdbcUrl(),
                postgresContainer.getUsername(),
                postgresContainer.getPassword());
    }
}
