package controllers.servlets;

import controllers.servlets.utils.ServletUtils;
import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "loginServlet", urlPatterns = {"", "/login"})
public class LoginServlet extends HttpServlet {

    private final String PASSWORD = "password";
    private final String REMEMBER = "remember";
    private final String LOGIN_COOKIE = "tweeter_login";
    private final String PASSWORD_COOKIE = "tweeter_password";
    private final String CHECKBOX_SELECTED = "on";
    private final int SECONDS_IN_DAY = 60 * 60 * 24;


    UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
        super.init();
    }

    /**
     * if user send request:
     * cookies will by check if any of logging dependend are stored in user browser,
     * if those cookies exist, their maxAge will be overided. And login and password would be attached to request as attribute.
     * and doPOST method will be called.
     * Otherwise login.jsp will be displated.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = null;
        String password = null;

        if (null != req.getCookies()) {
            for (Cookie cookie : req.getCookies()) {
                if (cookie.getName().equals(LOGIN_COOKIE)) {
                    login = cookie.getValue();
                    cookie.setMaxAge(SECONDS_IN_DAY);
                    resp.addCookie(cookie);
                } else if (cookie.getName().equals(PASSWORD_COOKIE)) {
                    password = cookie.getValue();
                    cookie.setMaxAge(SECONDS_IN_DAY);
                    resp.addCookie(cookie);
                }
            }
        }

        if (login != null && password != null) {
            req.setAttribute(ServletUtils.LOGIN, login);
            req.setAttribute(PASSWORD, password);
            doPost(req, resp);
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }


        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    /**
     * Firtly method try  get login, a=password from parameters (parameters are send by form
     * if parameters are mull it means that doPost me called by do Get() because doGet method
     * is putting attributes instead of parameters
     * In next step attributes are checked and ol and password are set to attributes values,
     * Login and password are verified by dao isValid method. If it is valid login is saved in session attributes.
     * Next if remember checkBox is se, cookies for login and password are created and attached to response.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(ServletUtils.LOGIN);
        String password = req.getParameter(PASSWORD);
        String remember = req.getParameter(REMEMBER);

        if (null == login || null == password) {
            login = (String) req.getAttribute(ServletUtils.LOGIN);
            password = (String) req.getAttribute(PASSWORD);
        }

        if (userDAO.isUserValid(login, password)) {
            req.getSession().setAttribute(ServletUtils.LOGIN, login);
            if (null != remember && remember.equals(CHECKBOX_SELECTED)) {
                Cookie loginCookie = new Cookie(LOGIN_COOKIE, login);
                Cookie passwordCookie = new Cookie(PASSWORD_COOKIE, password);
                loginCookie.setMaxAge(SECONDS_IN_DAY);
                passwordCookie.setMaxAge(SECONDS_IN_DAY);
                resp.addCookie(loginCookie);
                resp.addCookie(passwordCookie);
            }

            req.getRequestDispatcher("users").forward(req, resp);
        } else {
            req.setAttribute("hasError", "true");
            req.setAttribute("error", "Login or password incorrect!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }


//        PrintWriter writer = resp.getWriter();
//        writer.println("<html><body>My login is " + login + " and may password is " + password + "</body></html>");
//
//        req.getRequestDispatcher("/login.jsp").include(req, resp);

    }


}
