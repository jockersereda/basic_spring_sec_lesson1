package ua.com.owu.basic_spring_sec_lesson1.services.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.owu.basic_spring_sec_lesson1.dao.UserDAO;
import ua.com.owu.basic_spring_sec_lesson1.models.User;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDAO.findByUsername(s);
    }


    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public User findByToken(String token) {
        return userDAO.findByToken(token);
    }
}
