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

    /**
     * Adds a course to a user, the user will be searched by its username in the
     * persistence service, likewise the course will be searched by its name, if both exist
     * the course will be correctly added to user, otherwise the method throws an exception if
     * some data does not exist
     *
     * @param userName the username to search for
     * @param courseName the course name to search for
     * @return the user with the added course
     */
    User addCourseToUser(String userName, String courseName);

    /**
     * Adds a certain role to a user, the user will be searched by its username
     * and the role will be searched by its role name, if both exist in the persistence
     * service and no error occurs the role will be correctly added to the user
     * otherwise it will throw some <span style="color: red">exception</span>
     *
     * @param roleName the name of the role to search for
     * @param userName the name of the user to search for
     */
    void addRoleToUser(String roleName, String userName);
}
