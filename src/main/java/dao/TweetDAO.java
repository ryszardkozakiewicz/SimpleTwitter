package dao;

import model.Tweet;
import model.User;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class TweetDAO extends AbstractDao {

    UserDAO userDAO = new UserDAO();

    public void addTweet(String userLogin, String message) {

        User author = userDAO.getUserByLogin(userLogin);
        Tweet tweet = new Tweet();

        tweet.setPublishedAt(new Date());
        tweet.setAuthor(author);
        tweet.setMessage(message);

        hibernateUtil.save(tweet);
    }

    public void deleteTweet(String userLogin, long tweetId) {

        User userByLogin = userDAO.getUserByLogin(userLogin);
        Tweet tweet = entityManager.find(Tweet.class, tweetId);
        if (tweet.getAuthor().equals((userByLogin))) {
            hibernateUtil.delete(Tweet.class, tweetId);
        }
    }

    public List<Tweet> getFollowedTweet(String userLogin){

        User currentUser = userDAO.getUserByLogin(userLogin);
        Set<User> followedUser = currentUser.getFollowed();
        List<User> folloedWithCurrent = new ArrayList<>(followedUser);
        folloedWithCurrent.add(currentUser);

        Query query = entityManager.createQuery("SELECT t FROM Tweet t WHERE t.Author in :list");
        query.setParameter("list",folloedWithCurrent );
        return query.getResultList();
    }

}
