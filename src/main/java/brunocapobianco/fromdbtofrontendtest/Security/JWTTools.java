package brunocapobianco.fromdbtofrontendtest.Security;
import brunocapobianco.fromdbtofrontendtest.Exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import brunocapobianco.fromdbtofrontendtest.Entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JWTTools
{
    @Value("${spring.jwt.secret}")
    private String secret;

    public String createToken(User user)
    {
        return Jwts.builder().subject(String.valueOf(user.getId_user()))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*24*7))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }
    public void verifyToken(String token)
    {
        try
        {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build();
        }
        catch (Exception ex)
        {
            throw new UnauthorizedException("Problemi con il token! Effettua il login");
        }
    }
    public String extractIdFromToken(String token)
    {
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }
}
