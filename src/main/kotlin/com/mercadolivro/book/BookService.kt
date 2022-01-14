package com.mercadolivro.book

import com.mercadolivro.customer.Customer
import com.mercadolivro.exception.Errors
import com.mercadolivro.exception.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {
    fun createBook(book: Book) {
        bookRepository.save(book)
    }

    fun findAll(pageable: Pageable): Page<Book> {
        return bookRepository.findAll(pageable)
    }

    fun findActives(pageable: Pageable): Page<Book> {
        return bookRepository.findByStatus(BookStatus.ACTIVE, pageable)
    }

    fun getById(id: Int): Book {
        return bookRepository.findById(id).orElseThrow { NotFoundException(Errors.ML100.message.format(id), Errors.ML100.code) }
    }

    fun delete(id: Int) {
        var book = getById(id)

        book.status = BookStatus.CALLED_OFF

        update(book)
    }

    fun update(book: Book) {
        bookRepository.save(book)

    }

    fun deleteByCustomer(customer: Customer) {
        bookRepository.findByCustomer(customer)

    }


}