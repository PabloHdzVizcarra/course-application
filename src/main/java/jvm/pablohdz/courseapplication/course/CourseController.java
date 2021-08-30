package jvm.pablohdz.courseapplication.course;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<List<Course>> fetchUsers() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }
}
