package com.mercadolivro.book

import com.fasterxml.jackson.annotation.JsonAlias
import com.mercadolivro.customer.Customer
import java.math.BigDecimal
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class BookRequest (

    @field:NotEmpty(message = "O nome deve ser informado")
    var name: String,

    @field:NotNull(message = "Price deve ser informado")
    var price: BigDecimal,

    @JsonAlias("customer_id")
    var customerId: Int
)