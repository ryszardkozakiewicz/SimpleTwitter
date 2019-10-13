package controllers.servlets;

import controllers.servlets.utils.ServletUtils;
import dao.UserDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FollowServlet", value = "/follow")
public class FollowServlet extends HttpServlet {

    UserDAO userDAO;

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
        String userToFollowLogin = req.getParameter("userLoginToFollow");
        userDAO.follow(currentUserLogin, userToFollowLogin);

        req.getRequestDispatcher("users").forward(req, resp);
    }
}
