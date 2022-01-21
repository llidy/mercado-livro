package com.mercadolivro.purchase

import com.mercadolivro.book.BookService
import com.mercadolivro.customer.CustomerService
import org.springframework.stereotype.Component

@Component
class PurchaseMapper (
    private val bookService: BookService,
    private val customerService: CustomerService
        ) {

    fun toModel(request: PurchaseRequest): Purchase {
        val customer = customerService.findById(request.customerId)
        val books = bookService.findAllByIds(request.bookIds)

        return Purchase(
            customer = customer,
            books = books.toMutableList(),
            price = books.sumOf { it.price }
        )
    }
}