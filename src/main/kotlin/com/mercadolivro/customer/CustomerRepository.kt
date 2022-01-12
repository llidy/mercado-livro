package com.mercadolivro.customer

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Int> {
    fun findByNameContaining(name: String, pageable: Pageable): Page<Customer>
}