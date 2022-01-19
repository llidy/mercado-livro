package com.mercadolivro.purchase

import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class PurchaseRequest (

    @field:NotNull
    @field:Positive
    val customerId: Int,

    @field:NotNull
    val bookIds: Set<Int>

)