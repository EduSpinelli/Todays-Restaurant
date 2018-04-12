package com.restaurant.choice.security.jwt.extractor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

@Component
public class JwtHeaderTokenExtractor implements TokenExtractor {
  public static final String HEADER_PREFIX = "Bearer ";

  @Override
  public String extract(String header) {
    if (StringUtils.isBlank(header)) {
      throw new AuthenticationServiceException("Authorization header cannot be blank!");
    }

    if (header.length() < HEADER_PREFIX.length()) {
      throw new AuthenticationServiceException("Invalid authorization header size.");
    }

    if (!header.startsWith(HEADER_PREFIX)) {
      throw new AuthenticationServiceException("Header has errors.");
    }

    return header.substring(HEADER_PREFIX.length(), header.length());
  }
}
