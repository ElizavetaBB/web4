package application.dao;

import application.entity.User;

public interface UserDAO {
    User save(User user);

    User findByUsername(String username);
}