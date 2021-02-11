package first.repository;

import first.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long> {
    List<User> findAllByName(String name);

    @Query(value = "select * from firsttable", nativeQuery = true)
    List<User> findAllUsers();
}
