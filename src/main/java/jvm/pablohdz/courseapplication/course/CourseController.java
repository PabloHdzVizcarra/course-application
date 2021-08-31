package jvm.pablohdz.courseapplication.course;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> saveCourse(@RequestBody Course resource) {
        return new ResponseEntity<>(
                courseService.saveCourse(resource),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> fetchCourses() {
        List<Course> courses = courseService.getAllCourses();
        List<CourseDTO> dtos = courses.stream()
                .map(CourseDTO::new)
                .collect(Collectors.toUnmodifiableList());

        return ResponseEntity.ok(dtos);
    }
}
