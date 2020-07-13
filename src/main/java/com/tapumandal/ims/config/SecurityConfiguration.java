package com.tapumandal.ims.config;

import com.tapumandal.ims.filters.JwtRequestFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private String apiVersionUrl = "/api/v1";

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

            http.csrf().disable()
                .authorizeRequests()
                .antMatchers(apiVersionUrl+"/registration").permitAll()
                .antMatchers(apiVersionUrl+"/authenticate").permitAll()
                .antMatchers(apiVersionUrl+"/dashboard").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(apiVersionUrl+"/admin").hasRole("ADMIN")
                .antMatchers(apiVersionUrl+"/user").hasRole("USER")
                .antMatchers(apiVersionUrl+"/user/**").hasAnyAuthority("ADMIN")
                .antMatchers(apiVersionUrl+"/product/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(apiVersionUrl+"/measurement/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(apiVersionUrl+"/challan/**").hasAnyAuthority("ADMIN", "USER")
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//                .anyRequest().authenticated()
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}