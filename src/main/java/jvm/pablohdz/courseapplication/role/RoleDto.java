package jvm.pablohdz.courseapplication.role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto {
    private RoleName name;

    public RoleDto(Role role) {
        this.name = role.getName();
    }
}
