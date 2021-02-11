package first;

import first.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@EnableSwagger2
public class App {
    private static final Logger userLogger = Logger.getLogger(App.class);
    private static final int BACKEND_PORT = 8083;

    /*@Autowired
    private UserService userService;*/

    public static void main(String args[]) {
        SpringApplication app = new SpringApplication(App.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", BACKEND_PORT));
        app.run(args);
    }

    /*@EventListener(ApplicationReadyEvent.class)
    private void testJpaMethods(){
        User user1 = new User();
        user1.setName("second");
        user1.setAge(23);
        userService.createUser(user1);

        userService.findAll().forEach(it-> userLogger.info("FIND ALL " + it));

        userService.findAllByName("first").forEach(it-> userLogger.info("FIND ALL BY NAME " + it));
    }*/
}
