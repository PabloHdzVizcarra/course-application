package jvm.pablohdz.courseapplication.user;

public interface UserService {
    /**
     * Stores a user in the implemented persistence service
     *
     * @param user The user to persists
     * @return The user stored in the persistence service
     */
    User saveUser(User user);
}
