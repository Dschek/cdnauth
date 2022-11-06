package com.cdn.auth.auth.db.service;

import com.cdn.auth.auth.db.model.Token;
import com.cdn.auth.auth.db.model.User;
import com.cdn.auth.auth.db.repo.TokenDB;
import com.cdn.auth.auth.db.repo.UserDB;
import com.cdn.auth.auth.mvc.model.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.Random;

@org.springframework.stereotype.Service
public class Service {
    private final UserDB userDB;
    private final TokenDB tokenDB;

    @Autowired
    public Service(UserDB userDB, TokenDB tokenDB) {
        this.userDB = userDB;
        this.tokenDB = tokenDB;
    }

    @Value("${register.can}")
    private boolean canRegister;

    public boolean registration(UserRequest userRequest){
        return canRegister && addUser(userRequest)!=null;
    }
    private User addUser(UserRequest userRequest){
        if(userRequest.getLogin() == null || userRequest.getPassword()==null){
            return  null;
        }
        try{
            String password = Hashing.sha256().hashString(userRequest.getPassword(), StandardCharsets.UTF_8).toString();
            return userDB.save(User.builder().login(userRequest.getLogin()).password(password).build());
        }catch (Exception e){}
        return  null;
    }
    private User validateUser(UserRequest userRequest){
        if(userRequest.getLogin() == null || userRequest.getPassword()==null){
            return  null;
        }
        String password = Hashing.sha256().hashString(userRequest.getPassword(), StandardCharsets.UTF_8).toString();
        return userDB.findUserByLoginAndPassword(userRequest.getLogin(), password);
    }

    public String addToken(UserRequest userRequest){
        User user = validateUser(userRequest);
        if(user != null) {
            try {
                String tokenString = generateRandomString(20);
                Token token = tokenDB.save(Token.builder().user(user).token(Hashing.sha256().hashString(tokenString, StandardCharsets.UTF_8).toString()).build());
                return tokenString;
            } catch (Exception e) {}
        }
        return null;
    }

    public String generateRandomString(int size){
        return new Random().ints(48, 122 + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(size)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
    public boolean validateToken(String token, String login){
        User user = userDB.findUserByLoginAndToken(login, token);
        if(user !=null){
            return true;
        }
        return false;
    }

}
