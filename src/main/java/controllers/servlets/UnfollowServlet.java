package controllers.servlets;

import controllers.servlets.utils.ServletUtils;
import dao.UserDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UnfollowServlet", value = "/unfollow")
public class UnfollowServlet extends HttpServlet {

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
        String userLoginToUnfollow = req.getParameter("userLoginToUnfollow");
        userDAO.stopFollowing(currentUserLogin, userLoginToUnfollow);

        req.getRequestDispatcher("users").forward(req, resp);
    }
}
