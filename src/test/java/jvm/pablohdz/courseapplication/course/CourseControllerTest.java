package jvm.pablohdz.courseapplication.course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CourseControllerTest {
    @Mock
    private CourseService courseService;
    private CourseController underTest;
    private Course basicCourse;

    @BeforeEach
    void setUp() {
        underTest = new CourseController(courseService);
        basicCourse = new Course(1L, "basic java",
                "Java", new ArrayList<>());
    }

    @Test
    void testThatSaveCourse() {
        given(courseService.saveCourse(any()))
                .willReturn(basicCourse);
        ResponseEntity<Course> response = underTest.saveCourse(new Course());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testThatFetchAllCourses() {
        ResponseEntity<List<CourseDTO>> response = underTest.fetchCourses();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}