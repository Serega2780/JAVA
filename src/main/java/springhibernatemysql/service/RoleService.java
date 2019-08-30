package springhibernatemysql.service;

import springhibernatemysql.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getSingleRole(int id);
}
