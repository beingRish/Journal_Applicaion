package being.rish.journalApp.config;

import being.rish.journalApp.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
Spring Security:
Spring Security is a powerful and highly customizable security framework,
    that is often used in Spring Boot applications to handle authentication and authorization.
Authentication: The process of verifying a user's identity(e.g., username and password)
Authorization: The process of granting or denying access to specific resources or actions
    based on the authenticated user's roles and permissions.

Configuration:
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
Once the dependency is added, Spring Boot's autoconfiguration feature will automatically apply security to the application.
By default, Spring Security uses HTTP Basic Authentication.
The client sends an Authorization header Authorization: Basic <encoded-string>
The server decodes the string, extracts the username and password, and verifies them.
    If they're correct, access is granted. Otherwise, an "Unauthorized" response is sent back.
Encoding Credentials are combined into a string like username:password which is then encoded using Base64.

@EnableWebSecurity: This annotation signals Spring to enable its web security support.
    This is what makes you application secured. It's used in conjunction with @Configuration.
WebSecurityConfigurerAdapter: It is a utility class in the Spring Security framework that provides default configuration and allows customization of certain features.
    By extending it, you can configure and customize Spring Security for your application needs.
configure(): This method provides a way to configure how requests are secured.
    It defines how request matching should be done and what security actions should be applied.
http.authorizeHttpRequests(): This tells Spring Security to start authorizing the requests.
.antMatchers("/hello").permitAll(): This part specifies that HTTP requests matching the path /hello should be permitted(allowed) for all users, whether they are authenticated or not.
.anyRequest().authenticated(): This is a more general matcher that specifies any request(not already matched by previous matchers) should b authenticated,
    meaning users have to provide valid credentials to access these endpoints.
.and(): This is a method to join several configurations. It helps to continue the configuration from the root(HttpSecurity).
.formLogin(): This enables form-method authentication. By default, it will provide a form for the user to enter their username and password.
    If the user is not authenticated, and they try to access secured endpoint, they'll b redirected to the default login form.
*/

@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests()
                .antMatchers("/journal/**","/user/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .anyRequest().permitAll()
            .and()
            .httpBasic();
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
