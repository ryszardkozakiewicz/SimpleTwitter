package controllers.servlets.utils;

import javax.servlet.http.HttpServletRequest;

public class ServletUtils {

    public static final String LOGIN = "login";
    public static String LOGIN_COOKIE = "tweeter_login";
    public static String PASSWORD_COOKIE = "tweeter_password";

    public static String getUserLoginFromSession(HttpServletRequest req){
        return (String)req.getSession().getAttribute(LOGIN);
    }
}
