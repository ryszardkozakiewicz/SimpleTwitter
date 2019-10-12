package controllers.servlets;

import dao.UserDAO;
import model.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registerServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    private final String PASSWORD = "password";
    private final String REPETED_PASSWORD = "passwordRepeated";
    private final String EMAIL = "email";
    private final String LOGIN = "login";
    private final String NAME = "username";
    private final String LASTNAME = "lastName";

    UserService service;

    @Override
    public void init() throws ServletException {
        UserDAO userDAO = new UserDAO();
        service = new UserService(userDAO);
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(LOGIN);
        String name = req.getParameter(NAME);
        String lastName = req.getParameter(LASTNAME);
        String email = req.getParameter(EMAIL);
        String password = req.getParameter(PASSWORD);
        String passwordRepeated = req.getParameter(REPETED_PASSWORD);


        if (!password.equals(passwordRepeated)) {
            req.setAttribute("hasError", "true");
            req.setAttribute("error", "Password are not equal");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }

        User user = new User();
        user.setLogin(login);
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);

        String registerStatus = service.registerUser(user);

        if (!registerStatus.equals(UserService.SUCCESS)) {
            req.setAttribute("hasError", "true");
            req.setAttribute("error", registerStatus);
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        } else {

            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
