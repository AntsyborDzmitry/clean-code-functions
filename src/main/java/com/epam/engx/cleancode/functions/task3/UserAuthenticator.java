package com.epam.engx.cleancode.functions.task3;

import com.epam.engx.cleancode.functions.task3.thirdpartyjar.SessionManager;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.User;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.UserService;

public abstract class UserAuthenticator implements UserService {

    private SessionManager sessionManager;

    public void login(String userName, String password) {
         loginUser(getUserByName(userName), password);
    }

    private void loginUser(User user, String password) {
        validatePassword(user, password);
        sessionManager.setCurrentUser(user);
    }

    private void validatePassword(User user, String password) {
        if (isPasswordCorrect(user, password)) {
            return;
        }
        throw new WrongPasswordException();
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
}
