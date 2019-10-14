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
import java.util.List;

@WebServlet(name = "MessagesServlet", value = "/messages")
public class MessagesServlet extends HttpServlet {

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
        List<Tweet> followedTweets = tweetDAO.getFollowedTweet(currentUserLogin);
        req.setAttribute("tweets", followedTweets);
        req.getRequestDispatcher("/messages.jsp").forward(req, resp);
    }
}
