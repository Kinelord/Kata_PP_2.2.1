package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    void update(User user);

    List<User> listUsers();

    User getUser(Long id);

    User getUser(String model, int series);
}
