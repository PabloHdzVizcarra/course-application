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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    void saveUserWithCorrectHashPassword() {
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

        BDDMockito.given(passwordEncoder.encode(ArgumentMatchers.anyString()))
                .willReturn(hashPassword);
        BDDMockito.given(userRepository.save(ArgumentMatchers.any()))
                .willReturn(userHash);

        User saveUser = underTest.saveUser(user);

        Assertions
                .assertEquals(saveUser.getPassword(), hashPassword);
        Assertions
                .assertEquals(user.getPassword(), hashPassword);
    }
}