package first;

import first.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
