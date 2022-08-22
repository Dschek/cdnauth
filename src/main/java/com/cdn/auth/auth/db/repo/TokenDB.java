package com.cdn.auth.auth.db.repo;

import com.cdn.auth.auth.db.model.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenDB extends CrudRepository<Token, Integer> {

    Token findTokenByToken(String token);
}
