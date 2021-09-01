package jvm.pablohdz.courseapplication.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jvm.pablohdz.courseapplication.course.Course;
import jvm.pablohdz.courseapplication.course.CourseNotFoundException;
import jvm.pablohdz.courseapplication.course.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User userFound = userRepository.findByUsername(username);
        if (userFound == null) {
            log.error("user not found");
            throw new UsernameNotFoundException("user not found in the database");
        }

        log.info("user {} found", username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userFound.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getName().toString())));


        return new org.springframework.security.core.userdetails.User(
                userFound.getUsername(),
                userFound.getPassword(),
                authorities
        );
    }

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

    // TODO: 8/31/21 rule the user only save one course different once
    @Override
    public User addCourseToUser(String userName, String courseName) {
        User userFound = userRepository.findByUsername(userName);
        if (userFound == null)
            throw new UserNotFoundException(userName);

        Course courseFound = courseRepository.findByName(courseName);
        if (courseFound == null)
            throw new CourseNotFoundException(courseName);

        userFound.getCourses().add(courseFound);

        log.info("The {} course was successfully added to user: {} ",
                courseFound.getName(), userFound.getName()
        );

        return userRepository.save(userFound);
    }
}
