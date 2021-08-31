package jvm.pablohdz.courseapplication.course;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jvm.pablohdz.courseapplication.user.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CourseDTO {
    private String name;
    private String tag;
    private List<Object> users = new ArrayList<>();

    public CourseDTO(Course course) {
        this.name = course.getName();
        this.tag = course.getTag();

        users = course.getUsers().stream()
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setUsername(user.getUsername());
                    return userDTO;
                })
                .collect(Collectors.toList());
    }
}
