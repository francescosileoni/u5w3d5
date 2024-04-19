package francescosileoni.u5w3d5.security;

import francescosileoni.u5w3d5.entities.User;
import francescosileoni.u5w3d5.exceptions.UnAuthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

public class Filter {
    @Component
    public class Filter {
        @Value("${jwt.secret}")
        private String secret;
        public String creatingT(User user){
            return Jwts.builder()
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis()+1000*60*60*24*14))
                    .subject(String.valueOf(user.getId()))
                    .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .compact();
        }
        public void verifyT(String token){
            try{
                Jwts.parser()
                        .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                        .build().parse(token);
            }catch (Exception ex){
                throw new UnAuthorizedException(ex.getMessage());
            }
        }
        public String extractT(String token){
            return Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build().parseSignedClaims(token).getPayload().getSubject();
        }
    }


}
