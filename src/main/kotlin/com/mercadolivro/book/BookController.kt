package com.mercadolivro.book

import com.mercadolivro.customer.CustomerService
import com.mercadolivro.customer.toBook
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("books")
class BookController(
    val bookService: BookService,
    val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBook(@RequestBody requestBook: BookRequest){
        val customer = customerService.getById(requestBook.customerId)
        bookService.createBook(requestBook.toBook(customer))
    }

    @GetMapping
      fun findAll(): List<Book>{
        return bookService.findAll()
      }

    @GetMapping("/actives")
    fun findActives(): List<Book>{
        return bookService.findActives()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): Book{
        return bookService.getById(id)
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