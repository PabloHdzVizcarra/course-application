package jvm.pablohdz.courseapplication.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository to handle persists users in the database
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Obtains a {@link User} from the database by means of its username
     *
     * @param username The username to find
     * @return A user from database
     */
    User getUserByUsername(String username);
}
