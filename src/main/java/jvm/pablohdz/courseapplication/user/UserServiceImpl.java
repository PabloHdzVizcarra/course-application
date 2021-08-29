package jvm.pablohdz.courseapplication.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import jvm.pablohdz.courseapplication.course.Course;
import jvm.pablohdz.courseapplication.course.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User userSaved;
        try {
            userSaved = userRepository.save(user);
        } catch (Exception e) {
            throw new EmailUserDuplicatedException(user.getEmail());
        }
        log.info("New user created with the username name is: {}", user.getUsername());
        return userSaved;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addCourseToUser(String userName, String courseName) {
        User userFromDatabase = userRepository.getUserByUsername(userName);
        Course courseFromDatabase = courseRepository.getCourseByName(courseName);
        System.out.println(userFromDatabase);
        System.out.println(courseFromDatabase);
        // TODO: 8/28/21 get user by name
        // TODO: 8/28/21 add course to user
        // TODO: 8/28/21 show log message
        // TODO: 8/28/21 response with ok
    }

}
