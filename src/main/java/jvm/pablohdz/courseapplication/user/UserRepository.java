package jvm.pablohdz.courseapplication.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Repository to handle persists users in the database
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Obtains a {@link User} from the database by means of its username
     *
     * @param username The username to find
     * @return A user from database
     */
    User findByUsername(String username);
}
