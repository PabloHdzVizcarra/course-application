package jvm.pablohdz.courseapplication.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import jvm.pablohdz.courseapplication.course.Course;
import jvm.pablohdz.courseapplication.course.CourseRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@DisplayName("UserService Tests")
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private User userFoundMock;

    private UserServiceImpl userServiceTest;

    private final String hashPassword = "akshjds879hn732hbdjsd";
    private Course courseMock;

    @BeforeEach
    void setUp() {
        userServiceTest =
                new UserServiceImpl(userRepository, courseRepository, passwordEncoder);

        userFoundMock = new User(
                null,
                "John",
                "Connor",
                32,
                "johngod",
                "admin123",
                Gender.MALE,
                "test@test.com",
                new ArrayList<>()
        );

        courseMock = new Course(1L, "basic javascript",
                "javascript", new ArrayList<>()
        );
    }

    @Test
    void saveUserWithCorrectHashPassword() {
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

        given(userRepository.save(ArgumentMatchers.any()))
                .willReturn(userHash);
        given(passwordEncoder.encode(anyString()))
                .willReturn(hashPassword);

        User saveUser = userServiceTest.saveUser(user);

        Assertions
                .assertEquals(saveUser.getPassword(), hashPassword);
        Assertions
                .assertEquals(user.getPassword(), hashPassword);
    }

    @Test
    void throwCustomExceptionWhenTheEmailIsDuplicatedInTheDatabase() {
        given(userRepository.save(ArgumentMatchers.any()))
                .willThrow(new EmailUserDuplicatedException("test@tes.com"));
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

        Assertions.assertThrows(EmailUserDuplicatedException.class, () ->
                userServiceTest.saveUser(user));
    }

    @Test
    void courseIsAddedToUser() {
        given(userRepository.findByUsername(anyString()))
                .willReturn(userFoundMock);
        given(courseRepository.findByName(anyString()))
                .willReturn(courseMock);
        given(userRepository.save(any()))
                .willReturn(userFoundMock);

        User user = userServiceTest
                .addCourseToUser("anonymous", "javascript");

        assertNotNull(user);
    }

}