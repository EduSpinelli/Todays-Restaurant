package com.restaurant.choice.security.jwt.extractor;

public interface TokenExtractor {
  String extract(String payload);
}
