package bstorm.akimts.restapi.config.jwt;

import static bstorm.akimts.restapi.config.SecurityConstants.*;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    private final UserDetailsService service;

    public JwtTokenProvider(UserDetailsService service) {
        this.service = service;
    }

    public String createToken(String username, List<String> roles){

        String token = JWT.create()
                .withSubject(username)
                .withExpiresAt( new Date( System.currentTimeMillis() + EXPIRATION_TIME))
                .withClaim("roles", roles)
                .sign(Algorithm.HMAC512(JWT_KEY));

        return TOKEN_PREFIX+token;
    }

    public String resolveToken(HttpServletRequest request){

        String token = request.getHeader(HEADER_KEY);
        if( token != null && token.startsWith(TOKEN_PREFIX) )
            return token.substring( TOKEN_PREFIX.length() );

        return null;

    }

    public boolean validateToken(String token){

        try{
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(JWT_KEY))
                    .build()
                    .verify( token.replace(TOKEN_PREFIX, "") );

            String username = decodedJWT.getSubject();
            Date exp = decodedJWT.getExpiresAt();

            return username != null && exp != null && exp.after(new Date(System.currentTimeMillis()));
        }
        catch (JWTVerificationException exception){
            return false;
        }

    }

    public Authentication getAuthentication( String token ){

        String username = JWT.decode(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
        UserDetails userDetails = service.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

    }

}
