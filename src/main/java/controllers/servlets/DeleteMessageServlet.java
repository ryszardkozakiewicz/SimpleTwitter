package controllers.servlets;

import controllers.servlets.utils.ServletUtils;
import dao.TweetDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteMessageServlet", value = "/deleteTweet")
public class DeleteMessageServlet extends HttpServlet {

    private TweetDAO tweetDAO;

    @Override
    public void init() throws ServletException {
        tweetDAO = new TweetDAO();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String currentUserLogin = ServletUtils.getUserLoginFromSession(req);
        String tweetId = req.getParameter("tweetId");
        tweetDAO.deleteTweet(currentUserLogin, Long.parseLong(tweetId));

        req.getRequestDispatcher("messages").forward(req, resp);
    }
}
