package com.mercadolivro.customer

import com.mercadolivro.book.Book
import com.mercadolivro.book.BookRequest
import com.mercadolivro.book.BookStatus
import com.mercadolivro.book.PutBookRequest

fun PutCustomerRequest.toCostumer(id: Int): Customer{
    return Customer(
        id = id,
        name = this.name,
        email = this.email
    )
}

fun CustomerRequest.toCostumer(): Customer{
    return Customer(
        name = this.name,
        email = this.email
    )
}

fun BookRequest.toBook(customer: Customer): Book {
    return Book(
        name = this.name,
        price = this.price,
        status = BookStatus.ACTIVE,
        customer = customer
    )

}

fun PutBookRequest.toBook(previousValue: Book): Book {
    return Book(
        id = previousValue.id,
        name = this.name ?: previousValue.name,
        price = this.price ?: previousValue.price,
        status = previousValue.status,
        customer = previousValue.customer
    )
}
