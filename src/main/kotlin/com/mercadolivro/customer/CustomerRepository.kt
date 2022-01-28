package com.mercadolivro.customer

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import javax.validation.constraints.Email

interface CustomerRepository : JpaRepository<Customer, Int> {
    fun findByNameContaining(name: String, pageable: Pageable): Page<Customer>
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Customer?
}