package jvm.pablohdz.courseapplication.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jvm.pablohdz.courseapplication.course.CourseDTO;
import jvm.pablohdz.courseapplication.role.Role;
import jvm.pablohdz.courseapplication.role.RoleDto;
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
    private Set<RoleDto> roles;

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

        this.roles = user.getRoles().stream()
                .map(role1 -> {
                    RoleDto dto = new RoleDto();
                    dto.setName(role1.getName());
                    return dto;
                })
                .collect(Collectors.toSet());
    }

}
