package bstorm.akimts.restapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
// @EnableWebSecurity
 @EnableGlobalMethodSecurity(
         prePostEnabled = true
 )
public class SecurityConfig extends WebSecurityConfigurerAdapter {

     @Bean
     public PasswordEncoder encoder(){
         return new BCryptPasswordEncoder();
     }

     @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.inMemoryAuthentication()
                 .withUser("admin")
                 .password(encoder().encode("pass"))
                 .authorities("ROLE_ADMIN", "ROLE_USER")
                 .and()
                 .withUser("user")
                 .password(encoder().encode("pass"))
                 .authorities("ROLE_USER");
     }

     @Override
     protected void configure(HttpSecurity http) throws Exception {
         http.csrf().disable()
                 .httpBasic();

         http.authorizeRequests()
                 .anyRequest().permitAll();

         // h2
         http.headers()
                 .frameOptions()
                 .disable();
     }
 }
