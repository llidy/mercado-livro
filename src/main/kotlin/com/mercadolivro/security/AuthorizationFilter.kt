package com.mercadolivro.security

import com.mercadolivro.exception.AuthenticationException
import com.mercadolivro.userdetails.UserDetailsCustomerService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthorizationFilter(
    authenticationManager: AuthenticationManager,
    private val userDetails: UserDetailsCustomerService,
    private val jwtUtil: JwtUtil
): BasicAuthenticationFilter(authenticationManager) {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorization = request.getHeader("Authorization")
        if(authorization != null && authorization.startsWith("Bearer")){
            val auth = getauthorization(authorization.split(" ")[1])
            SecurityContextHolder.getContext().authentication = auth
        }
        chain.doFilter(request, response)
    }

    private fun getauthorization(token: String): UsernamePasswordAuthenticationToken {
        if(!jwtUtil.isValidToke(token)){
            throw AuthenticationException("Token Inválido", "999")
        }
        val subject = jwtUtil.getSubject(token)
        val customer = userDetails.loadUserByUsername(subject)
        return UsernamePasswordAuthenticationToken(customer, null, customer.authorities)

    }
}