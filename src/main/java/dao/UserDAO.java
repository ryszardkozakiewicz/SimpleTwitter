package dao;

import model.User;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


public class UserDAO extends AbstractDao {

    public void saveUser(User user) {
        hibernateUtil.save(user);
    }

    public void deleteUser(long userId) {
        hibernateUtil.delete(User.class, userId);
    }

    public User getUserByEmail(String email) {

        TypedQuery<User> query = entityManager.createQuery("SELECT u from User u WHERE u.email = :email", User.class);
        return query.setParameter("email", email).getSingleResult();
    }

    public User getUserByLogin(String login) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u from User u WHERE u.login = :login", User.class);
        return query.setParameter("login", login).getSingleResult();

    }

    public List<User> getUsersByName(String name) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u from User u WHERE u.name = :name", User.class);
        return query.setParameter("name", name).getResultList();

    }

//    public List<User> getCos (String ttt1, String ttt2){
//        TypedQuery<User> query = entityManager.createQuery("SELECT u from User u WHERE u.name = :cos and u.lastName = :cos2", User.class);
//        query.setParameter("cos", ttt1);
//        query.setParameter("cos2", ttt2);
//        return query.getResultList();
//    }


    public boolean isUserValid(String login, String password) {
        Query query = entityManager.createQuery("SELECT COUNT(*) as cnt FROM User u WHERE u.login = :login and u.password = :password");
        query.setParameter("login", login);
        query.setParameter("password", password);
        Object singleResult = query.getSingleResult();
        return ((Long) singleResult > 0 ? true : false);

    }

    public List<User> getFollowedUser(String login) {
        User user = getUserByLogin(login);
        Long userId = user.getId();
        Query query = entityManager.createQuery("SELECT follows FROM User u WHERE u.id = :userId");
        return query.setParameter("userId", userId).getResultList();
    }

    public List<User> getNotFollowedUser(String login) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.login != :login");
        query.setParameter("login", login);
        //TODO implement query with left join relation to followed table

        List<User> users = query.getResultList();
        List<User> followedUsers = getFollowedUser(login);
        users.removeAll(followedUsers);
        return users;

    }

    public void follow(String currentUserLogin, String userLoginToFollow) {

        if (currentUserLogin != userLoginToFollow) {
            User currentUser = getUserByLogin(currentUserLogin);
            User userToFollow = getUserByLogin(userLoginToFollow);

            currentUser.getFollows().add(userToFollow);
            saveUser(currentUser);
        }

    }

    public void stopFollowing(String currentUserLogin, String userLoginToStopFollow){
        if (currentUserLogin != userLoginToStopFollow) {
            User currentUser = getUserByLogin(currentUserLogin);
            User userToStopFollowing = getUserByLogin(userLoginToStopFollow);

            currentUser.getFollows().remove(userToStopFollowing);
            saveUser(currentUser);
        }

    }

    public List<User> getAllUsers(){

        TypedQuery typedQuery = entityManager.createQuery("SELECT u FROM User u", User.class);
        return typedQuery.getResultList();
    }
}


