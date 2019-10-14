package controllers.servlets;

import controllers.servlets.utils.ServletUtils;
import dao.TweetDAO;
import model.Tweet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddMessageServlet", value = "/addMessage")
public class AddMessageServlet extends HttpServlet {

    private TweetDAO tweetDAO;

    @Override
    public void init() throws ServletException {
        tweetDAO = new TweetDAO();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentUserLogin = ServletUtils.getUserLoginFromSession(req);
        String tweetMessage = req.getParameter("tweetMessage");
        tweetDAO.addTweet(currentUserLogin, tweetMessage);
        req.getRequestDispatcher("messages").forward(req, resp);
    }
}
