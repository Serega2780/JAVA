package org.sss.springMVC.repository;

import org.springframework.data.repository.CrudRepository;
import org.sss.springMVC.domain.User;


public interface UserRepository extends CrudRepository<User, Integer>
{

}
