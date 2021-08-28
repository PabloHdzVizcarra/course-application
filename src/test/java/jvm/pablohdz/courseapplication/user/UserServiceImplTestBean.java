package jvm.pablohdz.courseapplication.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jvm.pablohdz.courseapplication.course.CourseRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class UserServiceImplTestBean {
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, courseRepository, passwordEncoder);
    }

    @Test
    void withCorrectEmailCorrectHashPassword() {
        String hashPassword = "akshjds879hn732hbdjsd";
        User user = new User(
                null,
                "John",
                "Connor",
                32,
                "johngod",
                "admin123",
                Gender.MALE,
                "test@test.com",
                null
        );

        User userHash = new User(
                null,
                "John",
                "Connor",
                32,
                "johngod",
                hashPassword,
                Gender.MALE,
                "test@test.com",
                null
        );

        Mockito.when(passwordEncoder.encode(user.getPassword()))
                .thenReturn(hashPassword);
        Mockito.when(userRepository.save(user))
                .thenReturn(userHash);

        User saveUser = userService.saveUser(user);

        Assertions
                .assertEquals(saveUser.getPassword(), user.getPassword());
    }
}