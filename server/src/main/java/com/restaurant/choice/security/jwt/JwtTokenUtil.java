package com.restaurant.choice.security.jwt;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.restaurant.choice.security.configuration.JwtSettings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;

class JwtTokenUtil implements Serializable {

    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_CREATED = "iat";
    private static final long serialVersionUID = -3301605591108950415L;
    protected final Clock clock = DefaultClock.INSTANCE;

    @Autowired protected JwtSettings jwtSettings;

    protected Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    protected <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final var claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    protected Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSettings.getSecret()).parseClaimsJws(token).getBody();
    }

    protected Boolean isTokenExpired(String token) {
        final var expiration = getExpirationDateFromToken(token);
        return expiration.before(clock.now());
    }

    protected Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    protected Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return false;
    }

    protected String doGenerateToken(Map<String, Object> claims, String subject) {
        final var currentTime =
            LocalDateTime.ofInstant(clock.now().toInstant(), ZoneId.systemDefault());

        return Jwts.builder().setClaims(claims).setSubject(subject)
            .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
            .setExpiration(Date.from(currentTime.plusMinutes(jwtSettings.getTokenExpirationTime())
                .atZone(ZoneId.systemDefault()).toInstant()))
            .signWith(SignatureAlgorithm.HS512, jwtSettings.getSecret()).compact();
    }

}
