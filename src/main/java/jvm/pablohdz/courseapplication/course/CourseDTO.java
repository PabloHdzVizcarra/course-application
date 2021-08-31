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
    private List<UserDTO> users = new ArrayList<>();

    public CourseDTO(Course course) {
        this.name = course.getName();
        this.tag = course.getTag();

        users = course.getUsers().stream()
                .map(user -> {
                    UserDTO dto = new UserDTO();
                    dto.setName(user.getName());
                    dto.setLastName(user.getLastname());
                    dto.setUsername(user.getUsername());
                    dto.setAge(user.getAge());
                    dto.setEmail(user.getEmail());
                    dto.setGender(user.getGender());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
