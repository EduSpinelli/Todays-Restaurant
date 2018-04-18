package com.restaurant.choice.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class JwtToken extends JwtTokenUtil {

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails) {
        var claims = new HashMap<String, Object>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        var user = (JwtUser) userDetails;
        final var username = getUsernameFromToken(token);
        final var created = getIssuedAtDateFromToken(token);
        // final Date expiration = getExpirationDateFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token)
            && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate()));
    }

}
