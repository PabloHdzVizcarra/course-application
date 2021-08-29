package jvm.pablohdz.courseapplication.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import jvm.pablohdz.courseapplication.pojo.CourseToUserForm;
import jvm.pablohdz.courseapplication.utils.UrlUtils;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI url = UrlUtils.createUrl("/api/user");
        return ResponseEntity
                .created(url)
                .body(userService.saveUser(user));
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/save-course")
    public void addCourseToUser(@RequestBody CourseToUserForm resource) {
        userService.addCourseToUser(resource.getUserName(), resource.getCourseName());
        // TODO: 8/28/21 response with ok
    }
}
