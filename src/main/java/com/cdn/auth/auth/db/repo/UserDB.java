package com.cdn.auth.auth.db.repo;

import com.cdn.auth.auth.db.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDB extends CrudRepository<User, Integer> {
    User findUserByLoginAndPassword(String login, String password);

}
