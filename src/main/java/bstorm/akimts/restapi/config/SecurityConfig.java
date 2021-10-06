package bstorm.akimts.restapi.config;

import bstorm.akimts.restapi.config.jwt.JwtAuthenticationFilter;
import bstorm.akimts.restapi.config.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
     prePostEnabled = true,// @PreAuthorize et @PostAuthorize
     jsr250Enabled = true, // @RoleAllowed
     securedEnabled = true // @Secured
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // AuthenticationManagerBuilder utilisera
//    private final UserDetailsService service;
    private final JwtTokenProvider tokenProvider;

    public SecurityConfig(/*UserDetailsService service,*/ JwtTokenProvider tokenProvider) {
//        this.service = service;
        this.tokenProvider = tokenProvider;
    }

//    @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.userDetailsService(service);
//     }

     @Override
     protected void configure(HttpSecurity http) throws Exception {
         http.csrf().disable()
                 .httpBasic();

         http.authorizeRequests()
                 .antMatchers(HttpMethod.POST, "/user/**").hasAuthority("ADMIN")
                 .antMatchers(HttpMethod.PUT, "/user/**").hasAuthority("ADMIN")
                 .antMatchers(HttpMethod.DELETE, "/user/**").hasAuthority("ADMIN")
                 .antMatchers("/h2-console/**").permitAll()
                 .antMatchers("/hello/**").permitAll()
                 .antMatchers(SecurityConstants.LOGIN_URL).permitAll()
                 .antMatchers(SecurityConstants.REGISTER_URL).permitAll()
                 .anyRequest().authenticated();

         // configuration des filtres
         http.addFilterBefore(new JwtAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);
         http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

         // h2
         http.headers()
                 .frameOptions()
                 .disable();
     }

     @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
