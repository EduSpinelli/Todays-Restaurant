package com.restaurant.choice.security.controller;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.restaurant.choice.security.exceptions.AuthenticationException;
import com.restaurant.choice.security.jwt.JwtToken;
import com.restaurant.choice.security.model.AuthenticationRequest;
import com.restaurant.choice.security.repository.UserSecurityRepository;
import com.restaurant.choice.security.service.JwtAuthenticationResponse;

@RestController
public class AuthenticationController {

  private AuthenticationManager authenticationManager;
  private JwtToken jwtToken;
  private UserDetailsService userDetailsService;

  @Autowired
  public AuthenticationController(AuthenticationManager authenticationManager, JwtToken jwtToken,
      @Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService,
      UserSecurityRepository userSecurityRepository) {
    this.authenticationManager = authenticationManager;
    this.jwtToken = jwtToken;
    this.userDetailsService = userDetailsService;
  }

  @RequestMapping(value = "${route.authentication.auth}", method = RequestMethod.POST)
  public ResponseEntity<JwtAuthenticationResponse> createAuthenticationToken(
      @RequestBody AuthenticationRequest authenticationRequest) {

    authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

    // Reload password post-security so we can generate the token
    final UserDetails userDetails =
        userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    final String token = jwtToken.generateToken(userDetails);

    // Return the token
    return ResponseEntity.ok(new JwtAuthenticationResponse(token));
  }



  @ExceptionHandler({AuthenticationException.class})
  public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
  }

  /**
   * Authenticates the user. If something is wrong, an {@link AuthenticationException} will be
   * thrown
   */
  private void authenticate(String username, String password) {
    Objects.requireNonNull(username);
    Objects.requireNonNull(password);

    try {
      authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      throw new AuthenticationException("User is disabled!", e);
    } catch (BadCredentialsException e) {
      throw new AuthenticationException("Bad credentials!", e);
    }
  }
}
