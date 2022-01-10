package com.mercadolivro.book

import com.mercadolivro.customer.Customer
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<Book, Int> {
        fun findByStatus(status: BookStatus): List<Book>
        fun findByCustomer(customer: Customer): List<Book>
}