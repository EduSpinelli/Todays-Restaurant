package com.restaurant.choice.security.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.restaurant.choice.security.configuration.JwtSettings;
import com.restaurant.choice.security.exceptions.AuthenticationException;
import com.restaurant.choice.security.jwt.JwtTokenUtil;
import com.restaurant.choice.security.jwt.JwtUser;
import com.restaurant.choice.security.jwt.extractor.TokenExtractor;
import com.restaurant.choice.security.model.AuthenticationRequest;
import com.restaurant.choice.security.model.Authority;
import com.restaurant.choice.security.model.AuthorityName;
import com.restaurant.choice.security.model.UserSecurity;
import com.restaurant.choice.security.repository.UserSecurityRepository;
import com.restaurant.choice.security.service.JwtAuthenticationResponse;

@RestController
public class AuthenticationRestController {

  @Autowired
  private JwtSettings jwtSettings;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  @Qualifier("jwtUserDetailsService")
  private UserDetailsService userDetailsService;

  @Autowired
  private UserSecurityRepository applicationUserRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  @Qualifier("jwtHeaderTokenExtractor")
  private TokenExtractor tokenExtractor;

  @RequestMapping(value = "${route.authentication.signup}", method = RequestMethod.POST)
  public void signUp(@RequestBody UserSecurity user) {
    List<Authority> list = new ArrayList<>();
    list.add(new Authority(2L, AuthorityName.ROLE_ADMIN));
    UserSecurity newUser =
        new UserSecurity(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()),
            user.getFirstname(), user.getLastname(), user.getEmail(), true, list);

    applicationUserRepository.save(newUser);
  }

  @RequestMapping(value = "${route.authentication.auth}", method = RequestMethod.POST)
  public ResponseEntity<JwtAuthenticationResponse> createAuthenticationToken(
      @RequestBody AuthenticationRequest authenticationRequest) {

    authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

    // Reload password post-security so we can generate the token
    final UserDetails userDetails =
        userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    final String token = jwtTokenUtil.generateToken(userDetails);

    // Return the token
    return ResponseEntity.ok(new JwtAuthenticationResponse(token));
  }

  @RequestMapping(value = "${route.authentication.refresh}", method = RequestMethod.GET)
  public ResponseEntity<JwtAuthenticationResponse> refreshAndGetAuthenticationToken(
      HttpServletRequest request) {
    final String token = tokenExtractor.extract(request.getHeader(jwtSettings.getHeader()));
    String username = jwtTokenUtil.getUsernameFromToken(token);
    JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

    if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
      String refreshedToken = jwtTokenUtil.refreshToken(token);
      return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
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
