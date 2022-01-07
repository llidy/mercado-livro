package com.mercadolivro.book

import com.fasterxml.jackson.annotation.JsonAlias
import com.mercadolivro.customer.Customer
import java.math.BigDecimal
import javax.persistence.*

data class BookRequest (

    var name: String,

    var price: BigDecimal,

    @JsonAlias("customer_id")
    var customerId: Int
)