package com.restaurant.choice.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class RefreshToken extends JwtTokenUtil {

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final var created = getIssuedAtDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset) && (
            !isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public String refreshToken(String token) {
        final var currentTime =
            LocalDateTime.ofInstant(clock.now().toInstant(), ZoneId.systemDefault());

        final var claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()));
        claims.setExpiration(Date.from(currentTime.plusMinutes(jwtSettings.getRefreshTokenExpTime())
            .atZone(ZoneId.systemDefault()).toInstant()));

        return Jwts.builder().setClaims(claims)
            .signWith(SignatureAlgorithm.HS512, jwtSettings.getSecret()).compact();
    }


}
