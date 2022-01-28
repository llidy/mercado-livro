package com.mercadolivro.config

import com.mercadolivro.customer.CustomerRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import security.AuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val customerRepository: CustomerRepository
): WebSecurityConfigurerAdapter() {

    private val PUBLIC_MATCHERS = arrayOf<String>()

    private val PUBLIC_POST_MATCHERS = arrayOf(
        "/customer",
        "purchases"
    )
    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
        http.authorizeHttpRequests()
            .antMatchers(*PUBLIC_MATCHERS).permitAll()
            .antMatchers(HttpMethod.POST, *PUBLIC_POST_MATCHERS).permitAll()
            .anyRequest().authenticated()
        http.addFilter(AuthenticationFilter(authenticationManager(), customerRepository))

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

    }

    @Bean
    fun bCryptPasswordEnconder(): BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }
}