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
    fun createBook(bookModel: BookModel) {
        bookRepository.save(bookModel)
    }

    fun findAll(pageable: Pageable): Page<BookModel> {
        return bookRepository.findAll(pageable)
    }

    fun findActives(pageable: Pageable): Page<BookModel> {
        return bookRepository.findByStatus(BookStatus.ACTIVE, pageable)
    }

    fun getById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow { NotFoundException(Errors.ML100.message.format(id), Errors.ML100.code) }
    }

    fun delete(id: Int) {
        var book = getById(id)

        book.status = BookStatus.CALLED_OFF

        update(book)
    }

    fun update(bookModel: BookModel) {
        bookRepository.save(bookModel)

    }

    fun deleteByCustomer(customer: Customer) {
        bookRepository.findByCustomer(customer)

    }

    fun findAllByIds(bookIds: Set<Int>): List<BookModel> {
        return bookRepository.findAllById(bookIds).toList()
    }


}