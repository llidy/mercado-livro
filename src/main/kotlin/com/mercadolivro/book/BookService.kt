package com.mercadolivro.book

import com.mercadolivro.customer.Customer
import com.mercadolivro.exception.BadRequestException
import com.mercadolivro.exception.Errors
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.purchase.Purchase
import com.mercadolivro.purchase.PurchaseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import kotlin.reflect.jvm.internal.impl.util.ModuleVisibilityHelper
import kotlin.collections.contains as contains

@Service
class BookService(
    private val bookRepository: BookRepository,
    private val purchaseService: PurchaseService
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

    fun purchace(books: MutableList<BookModel>) {

        books.map {
            it.status = BookStatus.SOLD
        }

        bookRepository.saveAll(books)
    }

}