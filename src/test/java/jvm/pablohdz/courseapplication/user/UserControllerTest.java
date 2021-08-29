package jvm.pablohdz.courseapplication.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserControllerTest {
    private UserController userController;

    @Mock
    private UserService userService;


    @BeforeEach
    void setUp() {
        userController = new UserController(userService);
    }

    @Test
    void createUserWhenDataRequestIsCorrect() {
        ResponseEntity<User> response = userController.saveUser(new User());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}