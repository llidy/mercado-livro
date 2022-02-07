package com.mercadolivro.book

import com.mercadolivro.customer.CustomerService
import com.mercadolivro.customer.toBook
import com.mercadolivro.customer.toResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("books")
class BookController(
    private val bookService: BookService,
    private val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBook(@RequestBody @Valid requestBook: BookRequest){
        val customer = customerService.findById(requestBook.customerId)
        bookService.createBook(requestBook.toBook(customer))
    }

    @GetMapping
    fun findAll(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse>{
        return bookService.findAll(pageable).map { it.toResponse() }
    }

    @GetMapping("/actives")
    fun findActives(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse>{
        return bookService.findActives(pageable).map { it.toResponse() }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): BookResponse{
        return bookService.getById(id).toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int){
        bookService.delete(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody book: PutBookRequest){
        var bookSaved = bookService.getById(id)
        bookService.update(book.toBook(bookSaved))
    }

}