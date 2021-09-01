package jvm.pablohdz.courseapplication.role;

import java.util.List;

public interface RoleService {
    /**
     * Stores a role in the implemented persistence service.
     *
     * @param role the object role to persistence
     * @return role saved in the persistence service
     */
    Role saveRole(Role role);

    /**
     * Fetch all roles form the persistence service
     *
     * @return a list of roles or null list
     */
    List<Role> findRoles();

    /**
     * Searches for a role by name in the persistence service
     *
     * @param roleName the name of the role to find
     * @return a role from the persistence service or null
     */
    Role findByRoleName(RoleName roleName);
}
