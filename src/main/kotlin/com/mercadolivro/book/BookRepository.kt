package com.mercadolivro.book

import com.mercadolivro.customer.Customer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Int> {
        fun findByStatus(status: BookStatus, pageable: Pageable): Page<Book>
        fun findByCustomer(customer: Customer): List<Book>
}