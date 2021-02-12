package first;

import first.repository.UsersRepository;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//аннотация JUnit, указываем окружение, кто будет стартовать наши тесты
@RunWith(SpringRunner.class)
//Комбо-аннотация, говорит что мы запускаем тесты в окружении Sprong-boot приложения
@SpringBootTest
// Спринг пытается автоматически создать структуру классов, которая подменяет слой MVC
@AutoConfigureMockMvc
public class SecondControllerTest {
    private static final Logger userLogger = Logger.getLogger(SecondControllerTest.class);

    //Инджектим контроллер
    @Autowired
    private FirstController firstController;

    // Всё будет происходить в фейковом окружении
    @Autowired
    private MockMvc mockMvc;

   //Дергаем эндпойнт, который создает объект в базе
   // @Test
    public void fourthTest() throws Exception {
        Assert.assertNotNull(firstController);
        User user = new User();
        user.setName("third");
        user.setAge(3);
        this.mockMvc.perform(
                post("/users")
                        .param("name", user.getName())
                        .param("age", String.valueOf(user.getAge()))
        );
    }
}
