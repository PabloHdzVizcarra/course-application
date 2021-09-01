package jvm.pablohdz.courseapplication.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * Fetch a role from the persistence service by means of its role name
     *
     * @param roleName the role name to find
     * @return a role from the persistence service or null
     */
    Role findByName(String roleName);
}
