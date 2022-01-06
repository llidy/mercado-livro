package com.mercadolivro.customer

fun PutCustomerRequest.toCostumer(id: Int): Customer{
    return Customer(
        id = id,
        name = name,
        email = email
    )
}

fun CustomerRequest.toCostumer(): Customer{
    return Customer(
        name = name,
        email = email
    )
}