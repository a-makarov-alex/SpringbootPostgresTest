package first;

import first.repository.UsersRepository;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

public class ThirdControllerTest extends AbstractIntegrationTest {
    private static final Logger userLogger = Logger.getLogger(FirstControllerTest.class);

    @Autowired
    private WebApplicationContext ctx;
    @Autowired
    private UsersRepository repository;

   // @Test
    @Transactional
    public void givenUsersInDB_WhenUpdateStatusForNameModifyingQueryAnnotationJPQL_ThenModifyMatchingUsers(){
       // insertUsers();
       // int updatedUsersSize = userRepository.updateUserSetStatusForName(0, "SAMPLE");
        //assertThat(updatedUsersSize).isEqualTo(2);
    }
}
