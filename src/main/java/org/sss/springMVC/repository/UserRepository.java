package org.sss.springMVC.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.sss.springMVC.domain.User;

@Repository("userRepo")
public interface UserRepository extends CrudRepository<User, Integer>
{

}
