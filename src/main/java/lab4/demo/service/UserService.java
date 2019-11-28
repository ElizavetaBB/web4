package lab4.demo.service;


import lab4.demo.entity.User;

public interface UserService {
    User save(User user);

    User findByUsername(String username);
}
