package jvm.pablohdz.courseapplication.course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {
    @Mock
    private CourseRepository courseRepository;

    private CourseService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CourseServiceImpl(courseRepository);
    }

    @Test
    void testThatFetchAllCourses() {
        List<Course> users = underTest.getAllCourses();

        assertNotNull(users);
    }
}