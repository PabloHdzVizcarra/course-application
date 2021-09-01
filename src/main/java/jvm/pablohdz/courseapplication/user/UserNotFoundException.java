package jvm.pablohdz.courseapplication.user;

/**
 * the user does not exist in the persistence service
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("The user: " + username + " is not exists");
    }
}
