package springhibernatemysql.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springhibernatemysql.domain.User;
import springhibernatemysql.domain.UserRole;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

@Repository
//@Transactional
//@Stateful
public class UserDao {
    @PersistenceContext
    private EntityManager em;

    public User getUserByName(String name) {
        User user;
        user = em.createQuery("Select u from " + User.class.getName() + " u " + "Where u.name = :name", User.class)
                .setParameter("name", name).getSingleResult();
                /*
                ("Select ur.user from " + UserRole.class.getName() + " ur " +
                "Where ur.user.name = :name", User.class).setParameter("name", name).getSingleResult();
                 */
        return user;
    }

    public List<User> getUsers() {
        return em.createQuery("Select u from " + User.class.getName() + " u ", User.class).getResultList();
    }
}
