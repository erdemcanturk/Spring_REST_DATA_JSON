package com.example.demo.Repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findById(long id);

    List<User> findAll();

    List<User> findAllByEmailEndingWith(String email);



    @Query(
            value = "SELECT * FROM Users u WHERE u.email LIKE \"%?1\"",
            nativeQuery = true)
    List<User> findUserByStatusNative(String s);
}
