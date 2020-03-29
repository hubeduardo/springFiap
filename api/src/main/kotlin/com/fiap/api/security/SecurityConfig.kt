package com.fiap.api.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig : WebSecurityConfigurerAdapter() {

   override fun configure(http: HttpSecurity?) {
     http?.authorizeRequests()
            ?.anyRequest()
            ?.authenticated()?.and()
            ?.httpBasic()?.and()
            ?.sessionManagement()
            ?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)?.and()
            ?.csrf()?.disable()
    }
}
