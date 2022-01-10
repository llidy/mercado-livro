package com.mercadolivro.book

import com.mercadolivro.customer.Customer
import java.math.BigDecimal

data class BookResponse(

    var id: Int? = null,

    var name: String,

    var price: BigDecimal,

    var customer: Customer? = null,

    var status: BookStatus? = null
)
