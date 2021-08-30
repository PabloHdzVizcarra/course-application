package jvm.pablohdz.courseapplication.user;

import java.util.List;

public interface UserService {
    /**
     * Stores a user in the implemented persistence service
     *
     * @param user The user to persists
     * @return The user stored in the persistence service
     */
    User saveUser(User user);

    /**
     * Obtains all users stored in the persistence service
     *
     * @return An {@link User} list with all existing users
     */
    List<User> getAllUsers();

    User addCourseToUser(String userName, String courseName);
}
