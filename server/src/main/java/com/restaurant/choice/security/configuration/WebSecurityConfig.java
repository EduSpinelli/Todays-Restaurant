package com.restaurant.choice.security.configuration;

import java.util.Arrays;

import com.restaurant.choice.security.jwt.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.restaurant.choice.configuration.CustomCorsFilter;
import com.restaurant.choice.security.jwt.JwtAuthenticationEntryPoint;
import com.restaurant.choice.security.jwt.JwtAuthorizationTokenFilter;
import com.restaurant.choice.security.jwt.extractor.TokenExtractor;
import com.restaurant.choice.security.service.JwtUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtAuthenticationEntryPoint unauthorizedHandler;

  @Autowired
  private JwtToken jwtToken;

  @Autowired
  private JwtUserDetailsService jwtUserDetailsService;

  @Autowired
  @Qualifier("jwtHeaderTokenExtractor")
  private TokenExtractor tokenExtractor;

  @Autowired
  private JwtSettings jwtSettings;

  @Autowired
  private RouteAuthenticationSettings routeAuthenticationSettings;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(jwtUserDetailsService)
        .passwordEncoder(new PasswordEncoderConfig().passwordEncoder());
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    var permitAllEndpointList = Arrays.asList(routeAuthenticationSettings.getAuth(),
        routeAuthenticationSettings.getSignup(), "/console");
    JwtAuthorizationTokenFilter authenticationTokenFilter = new JwtAuthorizationTokenFilter(
        userDetailsService(), jwtToken, jwtSettings.getHeader(), tokenExtractor);

    httpSecurity.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests()
        .antMatchers(permitAllEndpointList.toArray(new String[0]))
        .permitAll().anyRequest().authenticated().and()
        .addFilterBefore(new CustomCorsFilter(), UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
        .headers().frameOptions().sameOrigin() // required to set for H2 else H2 Console will be
                                               // blank.
        .cacheControl();
  }

  @Override
  public void configure(WebSecurity web) {
    // AuthenticationTokenFilter will ignore the below paths
    web.ignoring()
        .antMatchers(HttpMethod.POST, routeAuthenticationSettings.getAuth(),
            routeAuthenticationSettings.getSignup())

        // allow anonymous resource requests
        .and().ignoring()
        .antMatchers(HttpMethod.GET, "/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css",
            "/**/*.js")

        // Un-secure H2 Database (for testing purposes, H2 console shouldn't be
        // unprotected in production)
        .and().ignoring().antMatchers("/h2-console/**/**");
  }
}
