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

@Component
public class JwtTokenUtil implements Serializable {

  static final String CLAIM_KEY_USERNAME = "sub";
  static final String CLAIM_KEY_CREATED = "iat";
  private static final long serialVersionUID = -3301605591108950415L;
  private Clock clock = DefaultClock.INSTANCE;

  @Autowired
  private JwtSettings jwtSettings;

  public String getUsernameFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  public Date getIssuedAtDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getIssuedAt);
  }

  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final var claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(jwtSettings.getSecret()).parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token) {
    final var expiration = getExpirationDateFromToken(token);
    return expiration.before(clock.now());
  }

  private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
    return (lastPasswordReset != null && created.before(lastPasswordReset));
  }

  private Boolean ignoreTokenExpiration(String token) {
    // here you specify tokens, for that the expiration is ignored
    return false;
  }

  public String generateToken(UserDetails userDetails) {
    var claims = new HashMap<String, Object>();
    return doGenerateToken(claims, userDetails.getUsername());
  }

  private String doGenerateToken(Map<String, Object> claims, String subject) {
    final var currentTime =
        LocalDateTime.ofInstant(clock.now().toInstant(), ZoneId.systemDefault());

    return Jwts.builder().setClaims(claims).setSubject(subject)
        .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
        .setExpiration(Date.from(currentTime.plusMinutes(jwtSettings.getTokenExpirationTime())
            .atZone(ZoneId.systemDefault()).toInstant()))
        .signWith(SignatureAlgorithm.HS512, jwtSettings.getSecret()).compact();
  }

  public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
    final var created = getIssuedAtDateFromToken(token);
    return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
        && (!isTokenExpired(token) || ignoreTokenExpiration(token));
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

  public Boolean validateToken(String token, UserDetails userDetails) {
    var user = (JwtUser) userDetails;
    final var username = getUsernameFromToken(token);
    final var created = getIssuedAtDateFromToken(token);
    // final Date expiration = getExpirationDateFromToken(token);
    return (username.equals(user.getUsername()) && !isTokenExpired(token)
        && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate()));
  }

}
