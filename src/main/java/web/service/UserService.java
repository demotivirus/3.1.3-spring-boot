package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void add(User user);

    List<User> listUsers();

    void delete(Long id);

    void edit(User user);

    User getUserFromId(Long id);

    User findUserByName(String name);
}
