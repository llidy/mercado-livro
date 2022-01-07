package com.mercadolivro.book

import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<Book, Int> {
        fun findByStatus(status: BookStatus): List<Book>
}