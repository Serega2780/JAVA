package springhibernatemysql.dao;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import springhibernatemysql.domain.Role;
import springhibernatemysql.domain.User;
import springhibernatemysql.domain.UserRole;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDao {
    @PersistenceContext
    private EntityManager em;


    public List<Role> getRolesById(int id) {
        List<Role> roles = em.createQuery("Select ur.role from " + UserRole.class.getName() + " ur " +
                "Where ur.user.id = :id", Role.class).setParameter("id", id).getResultList();
        System.out.println(roles);
        return roles;
    }

    public List<Role> getRoles() {
        List<Role> roles = em.createQuery("Select ur.role from " + Role.class.getName() + " ur "
                , Role.class).getResultList();
        System.out.println(roles);
        return roles;
    }

}
