package first;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class FirstControllerTest extends AbstractIntegrationTest{
    private static final Logger userLogger = Logger.getLogger(FirstControllerTest.class);

    /*@Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
    }*/

    /*@Test
    public void testSpringBootContext() throws Exception {
        try (Connection connection = getConnection(postgresContainer);

             userLogger.info("PG ");
    }*/
}
