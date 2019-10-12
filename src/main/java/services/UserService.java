package services;

import dao.UserDAO;
import model.User;

import javax.persistence.NoResultException;

public class UserService {

    public static final String EMAIL_ERROR = "email is already in use!";
    public static final String LOGIN_ERROR = "login is already in use!";
    public static final String SUCCESS = "success!=)";
    public static final String EMAIL_AND_LOGIN_ERROR = "emails already in use or login is already in use!";

    UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String registerUser(User user) {
        if (isEmailExist(user.getEmail()) && isLoginExist(user.getLogin())) {
            return EMAIL_AND_LOGIN_ERROR;
        } else if (isLoginExist(user.getLogin())) {
            return LOGIN_ERROR;
        } else if (isEmailExist(user.getEmail())) {
            return EMAIL_ERROR;
        }else {
            userDAO.saveUser(user);
            return SUCCESS;
        }
    }



    private boolean isLoginExist(String login) {
        try {
            userDAO.getUserByLogin(login);
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    private boolean isEmailExist(String email) {
        try {
            userDAO.getUserByEmail(email);
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }


}
