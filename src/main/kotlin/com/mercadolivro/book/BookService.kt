package com.mercadolivro.book

import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {
    fun createBook(book: Book) {
        bookRepository.save(book)
    }

    fun findAll(): List<Book> {
        return bookRepository.findAll().toList()
    }

    fun findActives(): List<Book> {
        return bookRepository.findByStatus(BookStatus.ACTIVE)
    }

    fun getById(id: Int): Book {
        return bookRepository.findById(id).orElseThrow()
    }

    fun delete(id: Int) {
        var book = getById(id)

        book.status = BookStatus.CALLED_OFF

        update(book)
    }

    fun update(book: Book) {
        bookRepository.save(book)

    }


}