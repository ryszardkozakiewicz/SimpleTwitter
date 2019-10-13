package controllers.servlets;

import controllers.servlets.utils.ServletUtils;
import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsersServlet", value = "/users")
public class UsersServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String currentUserLogin = ServletUtils.getUserLoginFromSession(req);
        List<User> followedUser = userDAO.getFollowedUser(currentUserLogin);
        List<User> notFollowedUser = userDAO.getNotFollowedUser(currentUserLogin);


        req.setAttribute("followedUsers", followedUser);
        req.setAttribute("notFollowedUsers", notFollowedUser);
        req.getRequestDispatcher("/users.jsp").forward(req, resp);

    }
}
