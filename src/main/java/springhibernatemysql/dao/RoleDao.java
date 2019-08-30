package springhibernatemysql.dao;

import org.springframework.stereotype.Repository;
import springhibernatemysql.domain.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDao {
    @PersistenceContext
    private EntityManager em;

    public Role getRole(int id) {

        return em.find(Role.class, id);
    }

    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(this.getRole(1)); //admin
        roles.add(this.getRole(2)); //user
        return roles;
    }

}
