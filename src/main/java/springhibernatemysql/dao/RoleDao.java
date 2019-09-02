package springhibernatemysql.dao;

import org.springframework.stereotype.Repository;
import springhibernatemysql.domain.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

public interface RoleDao {
    Role getRole(int id);

    List<Role> getRoles();

}
