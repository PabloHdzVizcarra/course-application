package jvm.pablohdz.courseapplication.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jvm.pablohdz.courseapplication.course.CourseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String name;
    private String lastName;
    private int age;
    private String username;
    private String email;
    private Gender gender;
    private List<CourseDTO> courses = new ArrayList<>();

    public UserDTO(User user) {
        this.name = user.getName();
        this.lastName = user.getLastname();
        this.age = user.getAge();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.gender = user.getGender();

        this.courses = user.getCourses().stream()
                .map(course -> {
                    CourseDTO dto = new CourseDTO();
                    dto.setName(course.getName());
                    dto.setTag(course.getTag());
                    return dto;
                })
                .collect(Collectors.toUnmodifiableList());
    }
}
