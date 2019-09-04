package springhibernatemysql.dao.implementation;

import org.springframework.stereotype.Repository;
import springhibernatemysql.dao.RoleDao;
import springhibernatemysql.domain.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Role getRole(int id) {

        return em.find(Role.class, id);
                //em.getReference(Role.class, id);
                //em.find(Role.class, id);
    }

    @Override
    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(this.getRole(1)); //admin
        roles.add(this.getRole(2)); //user
        return roles;
    }
}
