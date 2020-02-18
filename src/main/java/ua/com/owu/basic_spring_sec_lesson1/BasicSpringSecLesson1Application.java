package ua.com.owu.basic_spring_sec_lesson1;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class BasicSpringSecLesson1Application {

    public static void main(String[] args) {
        SpringApplication.run(BasicSpringSecLesson1Application.class, args);

        /*String token = Jwts.builder()
                .setSubject("xxx")
                .signWith(SignatureAlgorithm.HS512, "code key".getBytes())
                .setExpiration(new Date(System.currentTimeMillis() + 10000))
                .compact();
        System.out.println(token);

        String subject = Jwts.parser()
                .setSigningKey("code key".getBytes())
                .parseClaimsJws(token).getBody().getSubject();
        System.out.println(subject);*/
    }

}
