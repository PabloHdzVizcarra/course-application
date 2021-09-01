package jvm.pablohdz.courseapplication.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleResource {
    private final RoleService roleService;

    @Autowired
    public RoleResource(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        return ResponseEntity
                .created(null)
                .body(roleService.saveRole(role));
    }

    // TODO: 8/31/21 WAIT protected only users with ROLE_ADMIN
    // TODO: 8/31/21 - Response with RoleDto
    // TODO: 8/31/21 - Response with entity
    @GetMapping
    public List<Role> getRole() {
        return roleService.findRoles();
    }
}
