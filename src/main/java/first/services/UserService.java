package first.services;

import first.User;
import first.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Transactional
    public void createUser(User user) {
        usersRepository.save(user);
    }

    @Transactional
    public List<User> findAll(){
        return usersRepository.findAll();
    }

    @Transactional
    public List<User> findAllByName(String name){
        return usersRepository.findAllByName(name);
    }

    /*public Users findById(Long userId){
        return usersRepository.findById(userId).orElse(null);
    }

    public List<Users> findWhereEmailIsGmail(){
        return usersRepository.findWhereEmailIsGmail();
    }

    public List<Users> findWhereNameStartsFromSmith(){
        return usersRepository.findWhereNameStartsFromSmith();
    }*/
}
