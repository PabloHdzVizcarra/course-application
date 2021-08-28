package jvm.pablohdz.courseapplication.course;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseResource {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> saveCourse(@RequestBody Course resource) {
        return ResponseEntity
                .created(null)
                .body(courseService.saveCourse(resource));
    }
}
