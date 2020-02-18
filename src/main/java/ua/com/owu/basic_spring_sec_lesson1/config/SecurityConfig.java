package ua.com.owu.basic_spring_sec_lesson1.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ua.com.owu.basic_spring_sec_lesson1.services.user.UserService;

@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
/*    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.POST,"/**").authenticated() // - запит .POST може відправити тільки .authenticated()
                .antMatchers("/**").authenticated();  // - запит .GET може відправити тільки .authenticated()
    }*/

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeRequests()
               /* .antMatchers("/product/save").hasAnyRole("ADMIN")
                .antMatchers("/product/products").hasAnyRole("USER","ADMIN")
                .antMatchers("/product/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/saveUser").permitAll()
                *///.anyRequest().authenticated()  // - любий запит Request може робити тільки .authenticated()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .and()
                .addFilterBefore(new AllRequestFilter(userService), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new LoginFilter("/login", authenticationManager(), userService, passwordEncoder()),
                        UsernamePasswordAuthenticationFilter.class);
                //.httpBasic(); // .httpBasic() - це впроваджує базову архітектуру логінації через спеціальні хедери
    }

    private UserService userService;

    @Qualifier("userServiceImpl")
    private UserDetailsService userDetailsService;

    @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


    // настройка ролей
    /*@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}admin")
                .roles("ROLE_ADMIN");
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("{noop}user")
                .roles("ROLE_USER");
    }*/
}
