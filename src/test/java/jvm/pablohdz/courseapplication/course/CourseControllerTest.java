package jvm.pablohdz.courseapplication.course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourseControllerTest {
    @Mock
    private CourseService courseService;
    private CourseController underTest;

    @BeforeEach
    void setUp() {
        underTest = new CourseController(courseService);
    }

    @Test
    void testThatFetchAllCourses() {
        ResponseEntity<List<Course>> response = underTest.fetchUsers();

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }
}