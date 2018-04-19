package com.restaurant.choice.security.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.restaurant.choice.security.configuration.JwtSettings;
import com.restaurant.choice.security.jwt.JwtToken;
import com.restaurant.choice.security.jwt.JwtUser;
import com.restaurant.choice.security.jwt.RefreshToken;
import com.restaurant.choice.security.jwt.extractor.TokenExtractor;
import com.restaurant.choice.security.service.JwtAuthenticationResponse;


@RestController
public class RefreshController {

  private TokenExtractor tokenExtractor;
  private JwtToken jwtToken;
  private JwtSettings jwtSettings;
  private UserDetailsService userDetailsService;
  private RefreshToken refreshToken;

  @Autowired
  public RefreshController(@Qualifier("jwtHeaderTokenExtractor") TokenExtractor tokenExtractor,
      JwtToken jwtToken, JwtSettings jwtSettings,
      @Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService,
      RefreshToken refreshToken) {
    this.tokenExtractor = tokenExtractor;
    this.jwtToken = jwtToken;
    this.jwtSettings = jwtSettings;
    this.userDetailsService = userDetailsService;
    this.refreshToken = refreshToken;
  }


  @RequestMapping(value = "${route.authentication.refresh}", method = RequestMethod.GET)
  public ResponseEntity<JwtAuthenticationResponse> refreshAndGetAuthenticationToken(
      HttpServletRequest request) {
    final String token = tokenExtractor.extract(request.getHeader(jwtSettings.getHeader()));
    String username = jwtToken.getUsernameFromToken(token);
    JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

    if (refreshToken.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
      String refreshedToken = refreshToken.refreshToken(token);
      return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }

}
