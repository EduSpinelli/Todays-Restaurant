package com.restaurant.choice.security.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("route.authentication")
public class RouteAuthenticationSettings {

  private String auth;

  private String refresh;

  private String signup;

  public String getAuth() {
    return auth;
  }

  public void setAuth(String auth) {
    this.auth = auth;
  }

  public String getRefresh() {
    return refresh;
  }

  public void setRefresh(String refresh) {
    this.refresh = refresh;
  }

  public String getSignup() {
    return signup;
  }

  public void setSignup(String signup) {
    this.signup = signup;
  }

}
