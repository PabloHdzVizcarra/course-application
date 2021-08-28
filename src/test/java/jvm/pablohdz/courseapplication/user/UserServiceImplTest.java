package jvm.pablohdz.courseapplication.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jvm.pablohdz.courseapplication.course.CourseRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserServiceImpl underTest;
    private final String hashPassword = "akshjds879hn732hbdjsd";

    @BeforeEach
    void setUp() {
        underTest = new UserServiceImpl(userRepository, courseRepository, passwordEncoder);

        BDDMockito.given(passwordEncoder.encode(ArgumentMatchers.anyString()))
                .willReturn(hashPassword);
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

        BDDMockito.given(userRepository.save(ArgumentMatchers.any()))
                .willReturn(userHash);

        User saveUser = underTest.saveUser(user);

        Assertions
                .assertEquals(saveUser.getPassword(), hashPassword);
        Assertions
                .assertEquals(user.getPassword(), hashPassword);
    }

    @Test
    void throwCustomExceptionWhenTheEmailIsDuplicatedInTheDatabase() {
        BDDMockito.given(userRepository.save(ArgumentMatchers.any()))
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
                underTest.saveUser(user));
    }
}