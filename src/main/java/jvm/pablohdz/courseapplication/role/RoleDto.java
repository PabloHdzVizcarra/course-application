package jvm.pablohdz.courseapplication.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleDto {
    private RoleName name;

    public RoleDto(Role role) {
        this.name = role.getName();
    }
}
