package jvm.pablohdz.courseapplication.user;

import org.springframework.beans.factory.annotation.Autowired;
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
import jvm.pablohdz.courseapplication.role.Role;
import jvm.pablohdz.courseapplication.role.RoleName;
import jvm.pablohdz.courseapplication.role.RoleRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            CourseRepository courseRepository,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository
    ) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

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

    // TODO: 8/31/21 fetch role from repository
    // TODO: 8/31/21 -- create role not found exception
    // TODO: 8/31/21 -- added exception to handler
    // TODO: 8/31/21 -- throw error if role not exist
    // TODO: 8/31/21 add role to user
    // TODO: 8/31/21 save user with new role
    // TODO: 8/31/21 return user with role
    @Override
    public void addRoleToUser(RoleName roleName, String userName) {
        User userFound = userRepository.findByUsername(userName);
        if (userFound == null)
            throw new UserNotFoundException(userName);

        Role role = roleRepository.findByName(roleName);
        System.out.println(role);
    }
}
