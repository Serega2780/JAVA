package springhibernatemysql.dao.implementation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springhibernatemysql.dao.UserDao;
import springhibernatemysql.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public User getUserByName(String name) {
        User user;

        user = em.createQuery("Select u from " + User.class.getName() + " u " + "Where u.name = :name", User.class)
                .setParameter("name", name).getSingleResult();
        return user;
    }

    @Override
    public List<User> getUsers() {
        return em.createQuery("Select u from " + User.class.getName() + " u ", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getUsersByRole(String role) {
        List<User> users;

        users = em.createQuery("select u from " + User.class.getName() + " u ",
                User.class).getResultList();
        System.out.println(users.get(0).getAuthorities().contains("ROLE_ADMIN"));
        return users.stream().filter(u -> !u.getGrantedAuthorities().toString().contains("ROLE_ADMIN")).
                collect(Collectors.toList());

    }

    @Override
    public void removeUser(int id) {

        em.remove(this.getUserById(id));
    }

    @Override
    public void addUser(User user) {
        em.merge(user);
        em.flush();
    }
}
