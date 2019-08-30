package springhibernatemysql.service;

import org.springframework.stereotype.Service;
import springhibernatemysql.dao.RoleDao;
import springhibernatemysql.domain.Role;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public List<Role> getAllRoles() {
        return roleDao.getRoles();
    }

    @Override
    public Role getSingleRole(int id) {
        return roleDao.getRole(id);
    }
}
