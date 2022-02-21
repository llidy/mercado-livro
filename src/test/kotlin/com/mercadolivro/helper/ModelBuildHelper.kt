package com.mercadolivro.helper

import com.mercadolivro.book.BookModel
import com.mercadolivro.customer.Customer
import com.mercadolivro.customer.CustomerStatus
import com.mercadolivro.customer.Role
import com.mercadolivro.purchase.Purchase
import java.math.BigDecimal
import java.util.*

fun buildCustomer(
    id: Int? = null,
    name: String = "customer name",
    email: String= "${UUID.randomUUID()}@email.com",
    password: String = "password"
) = Customer(
    id = id,
    name = name,
    email = email,
    password = password,
    status = CustomerStatus.ACTIVE,
    roles = setOf(Role.CUSTOMER)
    )

fun buildPurchase(
    id: Int? = null,
    customer: Customer = buildCustomer(),
    books: MutableList<BookModel> = mutableListOf(),
    nfe: String? = UUID.randomUUID().toString(),
    price: BigDecimal = BigDecimal.TEN

) = Purchase (
    id = id,
    customer = customer,
    books = books,
    nfe = nfe,
    price = price
)