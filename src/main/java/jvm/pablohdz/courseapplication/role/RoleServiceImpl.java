package jvm.pablohdz.courseapplication.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role saveRole(Role role) {
        log.info("saved the role: {} in the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public List<Role> findRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
