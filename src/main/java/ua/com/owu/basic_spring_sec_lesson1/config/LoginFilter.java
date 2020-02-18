package ua.com.owu.basic_spring_sec_lesson1.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ua.com.owu.basic_spring_sec_lesson1.models.User;
import ua.com.owu.basic_spring_sec_lesson1.services.user.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {
    /*@Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("LOGIN FILTER WORK");
    }*/

    private User user;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public LoginFilter(String defaultFilterProcessarUrl, AuthenticationManager authenticationManager, UserService userService, PasswordEncoder passwordEncoder){
        super(new AntPathRequestMatcher(defaultFilterProcessarUrl));
        setAuthenticationManager(authenticationManager);
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        User user = new ObjectMapper().readValue(httpServletRequest.getInputStream(), User.class);
        if (user !=null) {
            System.out.println(user);
            // ? find user in database?
            User userDetails = (User) userService.loadUserByUsername(user.getUsername());
            this.user = userDetails;
            if (userDetails != null){
                boolean matches = passwordEncoder.matches(user.getPassword(),userDetails.getPassword());
                System.out.println(matches);
                if (matches){

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken
                            (userDetails.getUsername(), user.getPassword(), userDetails.getAuthorities());

                    Authentication authenticate = getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
                    return  authenticate;
                }
            }
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String token = Jwts.builder()
        .setSubject("xxx")
        .signWith(SignatureAlgorithm.HS512, "key".getBytes())
        .compact();

        this.user.setToken(token);
        userService.save(this.user);

        response.addHeader("Authorization", "Bearer" + token);
        chain.doFilter(request,response);

    }
}
