package bstorm.akimts.restapi.config.jwt;

import static bstorm.akimts.restapi.config.SecurityConstants.*;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    public String createToken(String username, List<String> roles){

        String token = JWT.create()
                .withSubject(username)
                .withExpiresAt( new Date( System.currentTimeMillis() + EXPIRATION_TIME))
                .withClaim("roles", roles)
                .sign(Algorithm.HMAC512(JWT_KEY));

        return TOKEN_PREFIX+token;
    }


}
