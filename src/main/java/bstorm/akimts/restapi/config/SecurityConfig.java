package bstorm.akimts.restapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
// @EnableWebSecurity
 @EnableGlobalMethodSecurity(
         prePostEnabled = true,// @PreAuthorize et @PostAuthorize
         jsr250Enabled = true, // @RoleAllowed
         securedEnabled = true // @Secured
 )
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService service;

    public SecurityConfig(UserDetailsService service) {
        this.service = service;
    }

    @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.userDetailsService(service);
     }

     @Override
     protected void configure(HttpSecurity http) throws Exception {
         http.csrf().disable()
                 .httpBasic();

         http.authorizeRequests()
                 .antMatchers(HttpMethod.POST, "/user/**").hasAuthority("ADMIN")
                 .antMatchers(HttpMethod.PUT, "/user/**").hasAuthority("ADMIN")
                 .antMatchers(HttpMethod.DELETE, "/user/**").hasAuthority("ADMIN")
                 .antMatchers(HttpMethod.GET, "/user/**").hasAuthority("USER")
                 .anyRequest().permitAll();

         // h2
         http.headers()
                 .frameOptions()
                 .disable();
     }
 }
