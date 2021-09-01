package jvm.pablohdz.courseapplication.pojo;

import jvm.pablohdz.courseapplication.role.RoleName;
import lombok.Data;

@Data
public class RoleToUserForm {
    private RoleName roleName;
    private String username;
}
