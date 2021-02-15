package first;

import first.services.UserService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FirstController {
    private static final Logger userLogger = Logger.getLogger(FirstController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> firstMethod() {
        List<User> users = new ArrayList<>();
        userService.findAll().forEach(it-> users.add(it));
        return users;
    }

    @GetMapping("/users/new")
    public List<User> firstMethodNew() {
        try {
            String urlString = "http://localhost:1080/test";
            URL url = new URL(urlString);
            HttpGet request = new HttpGet(urlString);
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(request);

            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            userLogger.info("CONTROLLER SEND REQUEST TO MOCKSERVER ENDPOINT AND GET RESPONSE CODE: " + response.getStatusLine().getStatusCode());
            userLogger.info("RESULT: " + result);

            // Пример с использованием HttpURLConnection
            /*HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            userLogger.info("CONTROLLER SEND REQUEST TO MOCKSERVER ENDPOINT AND GET RESPONSE CODE: " + con.getResponseCode());
            */
        } catch(IOException e) {
            userLogger.error("IOException " + e);
        }

        List<User> users = new ArrayList<>();
        userService.findAll().forEach(it-> users.add(it));
        return users;
    }

    @PostMapping("/users")
    public String createUser(
            @RequestParam(value="name") String name,
            @RequestParam(value="age") int age) {
        User user1 = new User();
        user1.setName(name);
        user1.setAge(age);
        userService.createUser(user1);
        return "success";
    }

}
