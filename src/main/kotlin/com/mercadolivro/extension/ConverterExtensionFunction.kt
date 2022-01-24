package com.mercadolivro.customer

import com.mercadolivro.book.*

fun PutCustomerRequest.toCostumer(previousValue: Customer): Customer{
    return Customer(
        id = previousValue.id,
        name = this.name,
        email = this.email,
        status = previousValue.status,
        password = previousValue.password
    )
}

fun CustomerRequest.toCostumer(): Customer{
    return Customer(
        name = this.name,
        email = this.email,
        status = CustomerStatus.ACTIVE,
        password = this.password
    )
}

fun BookRequest.toBook(customer: Customer): BookModel {
    return BookModel(
        name = this.name,
        price = this.price,
        status = BookStatus.ACTIVE,
        customer = customer
    )

}

fun PutBookRequest.toBook(previousValue: BookModel): BookModel {
    return BookModel(
        id = previousValue.id,
        name = this.name ?: previousValue.name,
        price = this.price ?: previousValue.price,
        status = previousValue.status,
        customer = previousValue.customer
    )
}

fun Customer.toResponse(): CustomerResponse{
    return CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status

    )
}

fun BookModel.toResponse(): BookResponse {
    return BookResponse(
        id = this.id,
        name = this.name,
        price = this.price,
        customer = this.customer,
        status = this.status
    )

}