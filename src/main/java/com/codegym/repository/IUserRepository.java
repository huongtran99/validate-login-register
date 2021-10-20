package com.codegym.repository;

import com.codegym.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IUserRepository extends CrudRepository<User, Long> {
    @Query(value = "select * from user where userName = ?1 and password = ?2", nativeQuery = true)
    User checkLogin(String userName, String password);
}
