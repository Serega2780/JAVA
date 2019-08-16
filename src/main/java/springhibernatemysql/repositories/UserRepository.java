package springhibernatemysql.repositories;

import org.springframework.data.repository.CrudRepository;
import springhibernatemysql.domain.User;

public interface UserRepository extends CrudRepository<User, Integer>
{

}
