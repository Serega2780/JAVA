package springhibernatemysql.dao;


import springhibernatemysql.domain.Role;
import java.util.List;

public interface RoleDao {
    Role getRole(int id);

    List<Role> getRoles();

}
