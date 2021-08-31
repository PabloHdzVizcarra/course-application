package jvm.pablohdz.courseapplication.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import jvm.pablohdz.courseapplication.pojo.CourseToUserForm;
import jvm.pablohdz.courseapplication.utils.UrlUtils;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody User user) {
        URI url = UrlUtils.createUrl("/api/user");
        User userFetched = userService.saveUser(user);
        UserDTO dto = new UserDTO(userFetched);
        return ResponseEntity
                .created(url)
                .body(dto);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDTO> dtos = users.stream()
                .map(UserDTO::new)
                .collect(Collectors.toUnmodifiableList());

        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/save-course")
    public ResponseEntity<UserDTO> addCourseToUser(@RequestBody CourseToUserForm resource) {
        User user = userService
                .addCourseToUser(resource.getUserName(), resource.getCourseName());
        UserDTO userDTO = new UserDTO(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userDTO);
    }
}
