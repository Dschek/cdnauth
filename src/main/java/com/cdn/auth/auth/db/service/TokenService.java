package com.cdn.auth.auth.db.service;

import com.cdn.auth.auth.db.model.Token;
import com.cdn.auth.auth.db.model.User;
import com.cdn.auth.auth.db.repo.TokenDB;
import com.cdn.auth.auth.mvc.model.UserRequest;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class TokenService {
    private final TokenDB tokenDB;

    @Autowired
    public TokenService(TokenDB tokenDB) {
        this.tokenDB = tokenDB;
    }

    public Token getTokenByToken(String token){
        try {
            return tokenDB.findTokenByToken(token);
        }catch (Exception e){
            System.out.println(e);
        }
        return  null;
    }

    public Token addToken(UserRequest userRequest){
        try{
            String password = Hashing.sha256().hashString(userRequest.getPassword(), StandardCharsets.UTF_8).toString();
        //    tokenDB.save(Token.builder().token(password).user(User.builder().id()))
            return null;
        }catch (Exception e){}return null;
    }


    public boolean validateToken(UserRequest userRequest){
        Token token = getTokenByToken(userRequest.getToken());
        if(token !=null && token.getUser().getLogin().equals(userRequest.getLogin())){
            return true;
        }
        return false;
    }
}
