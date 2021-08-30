package jvm.pablohdz.courseapplication.course;

import org.springframework.stereotype.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllUsers() {
        List<Course> courses = courseRepository.findAll();
        log.info("Fetch all courses from persistence service number is {} ",
                courses.size());
        return courses;
    }
}
