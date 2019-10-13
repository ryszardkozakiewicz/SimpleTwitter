package model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "login")
    private String login;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "date_of_registration")
    private Date dateOfRegistration;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "follows_followers",
            joinColumns = {@JoinColumn(name = "follows_id")},
            inverseJoinColumns = {@JoinColumn(name = "followed_id")})
    private Set<User> follows = new HashSet<>();

    @ManyToMany(mappedBy = "follows")
    private Set<User> followed = new HashSet<>();

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastnName) {
        this.lastName = lastnName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<User> getFollows() {
        return follows;
    }

    public void setFollows(Set<User> follows) {
        this.follows = follows;
    }

    public Set<User> getFollowed() {
        return followed;
    }

    public void setFollowed(Set<User> followed) {
        this.followed = followed;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dateOfRegistration=" + dateOfRegistration +
                ", follows=" + follows +
                ", followed=" + followed +
                '}';
    }

    public static class UserBuilder {

        User user = new User();

        public UserBuilder() {
        }

        public UserBuilder buildLogin(String login) {
            user.setLogin(login);
            return this;
        }

        public UserBuilder buildName(String name) {
            user.setName(name);
            return this;
        }

        public UserBuilder buildLastName(String lastName) {
            user.setLastName(lastName);
            return this;
        }

        public UserBuilder buildEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public UserBuilder buildPassword(String password) {
            user.setPassword(password);
            return this;
        }

        public UserBuilder buildDateOfRegistration(Date dateOfRegistration) {
            user.setDateOfRegistration(dateOfRegistration);
            return this;
        }

        public User buildUser() {
            return this.user;

        }

    }
}



