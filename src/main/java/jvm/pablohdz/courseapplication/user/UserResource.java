package jvm.pablohdz.courseapplication.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import jvm.pablohdz.courseapplication.utils.UrlUtils;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserResource {
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

}
