package ua.com.owu.basic_spring_sec_lesson1.services.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.owu.basic_spring_sec_lesson1.models.User;

public interface UserService extends UserDetailsService {

    void save(User user);

    User findByToken(String token);
}
