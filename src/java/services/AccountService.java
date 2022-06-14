package services;

import models.User;

public class AccountService {
    public User login(String username, String password){
         if(username.equals("abe") && password.equals("password") || username.equals("barb") && password.equals("password")) {
            User user = new User(username, null);
            return user;
        }
        else {
            return null;
        }
    }
}