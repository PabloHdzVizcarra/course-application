package jvm.pablohdz.courseapplication.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

import jvm.pablohdz.courseapplication.course.Course;
import jvm.pablohdz.courseapplication.course.CourseNotFoundException;
import jvm.pablohdz.courseapplication.course.CourseRepository;
import jvm.pablohdz.courseapplication.role.Role;
import jvm.pablohdz.courseapplication.role.RoleName;
import jvm.pablohdz.courseapplication.role.RoleRepository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RoleRepository roleRepository;

    private User userFoundMock;
    private User basicUser;
    private User userWithHashPassword;
    private UserServiceImpl userServiceTest;
    private final String hashPassword = "akshjds879hn732hbdjsd";
    private Course courseMock;

    @BeforeEach
    void setUp() {
        userServiceTest =
                new UserServiceImpl(userRepository, courseRepository, passwordEncoder,
                        roleRepository
                );

        userFoundMock = new User(
                null,
                "John",
                "Connor",
                32,
                "johngod",
                "admin123",
                Gender.MALE,
                "test@test.com",
                new ArrayList<>(),
                new ArrayList<>()
        );

        courseMock = new Course(1L, "basic javascript",
                "javascript", new ArrayList<>()
        );

        basicUser = new User(
                null,
                "John",
                "Connor",
                32,
                "johngod",
                "admin123",
                Gender.MALE,
                "test@test.com",
                null,
                null
        );

        userWithHashPassword = new User(
                null,
                "John",
                "Connor",
                32,
                "johngod",
                hashPassword,
                Gender.MALE,
                "test@test.com",
                null,
                null
        );
    }

    @Test
    void testThatSaveUserWithCorrectHashPassword() {
        given(passwordEncoder.encode(anyString()))
                .willReturn(hashPassword);
        given(userRepository.save(any()))
                .willReturn(userWithHashPassword);

        User saveUser = userServiceTest.saveUser(basicUser);

        assertEquals(saveUser.getPassword(), hashPassword);
        assertEquals(basicUser.getPassword(), hashPassword);
    }

    @Test
    void testThatThrowCustomExceptionWhenTheEmailIsDuplicated() {
        given(userRepository.save(ArgumentMatchers.any()))
                .willThrow(new EmailUserDuplicatedException("test@tes.com"));

        assertThrows(EmailUserDuplicatedException.class, () ->
                userServiceTest.saveUser(basicUser));
    }

    @Test
    void testThatAddCourseToUser() {
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

    @Test
    void testThatThrowCustomExceptionWhenTheUserIsNotFound() {
        given(userRepository.findByUsername(anyString()))
                .willReturn(null);

        assertThrows(UserNotFoundException.class, () ->
                userServiceTest.addCourseToUser(
                        "notUser", "Javascript"));
    }

    @Test
    void testThatThrowCustomExceptionWhenTheCourseIsNotFound() {
        given(userRepository.findByUsername(anyString()))
                .willReturn(basicUser);
        given(courseRepository.findByName(anyString()))
                .willReturn(null);

        assertThrows(CourseNotFoundException.class, () ->
                userServiceTest.addCourseToUser("John", "errorCourse"));
    }

    @Nested
    class AddRoleToUser {

        @Test
        void testThatNotThrowExceptionFetchRoleByName() {
            given(userRepository.findByUsername(anyString()))
                    .willReturn(basicUser);
            given(roleRepository.findByName(any()))
                    .willReturn(new Role(null, RoleName.ROLE_ADMIN));


            Assertions.assertDoesNotThrow(() ->
                    userServiceTest.addRoleToUser(RoleName.ROLE_ADMIN, "john"));
        }
    }
}