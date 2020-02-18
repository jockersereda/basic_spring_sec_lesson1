package ua.com.owu.basic_spring_sec_lesson1.config;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import ua.com.owu.basic_spring_sec_lesson1.models.User;
import ua.com.owu.basic_spring_sec_lesson1.services.user.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AllRequestFilter extends GenericFilterBean {
    private User user;
    private UserService userService;
    public AllRequestFilter(UserService userService) {

        this.userService=userService;
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = null;

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        if(authorizationHeader != null){
            String token = authorizationHeader.replace("Bearer", "");
            User user =this.userService.findByToken(token);
            authentication = new UsernamePasswordAuthenticationToken(
                    user.getUsername(),user.getPassword(),user.getAuthorities());
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request,response);

    }
}
