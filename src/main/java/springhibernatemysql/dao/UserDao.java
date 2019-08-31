package springhibernatemysql.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springhibernatemysql.domain.Role;
import springhibernatemysql.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional

public class UserDao {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public User getUserByName(String name) {
        User user;

        user = em.createQuery("Select u from " + User.class.getName() + " u " + "Where u.name = :name", User.class)
                .setParameter("name", name).getSingleResult();
        return user;
    }

    public List<User> getUsers() {
        return em.createQuery("Select u from " + User.class.getName() + " u ", User.class).getResultList();
    }

    public User getUserById(int id) {
        return em.find(User.class, id);
    }

    public List<User> getUsersByRole(String role) {
        List<User> users;

        users = em.createQuery("select u from " + User.class.getName() + " u ",
                User.class).getResultList();
        return users.stream().filter(u->!u.getGrantedAuthorities().toString().contains("ROLE_ADMIN")).collect(Collectors.toList());

    }

    public void removeUser(int id) {

        em.remove(this.getUserById(id));
    }

    public void addUser(User user) {
        em.merge(user);
        em.flush();
    }
}
