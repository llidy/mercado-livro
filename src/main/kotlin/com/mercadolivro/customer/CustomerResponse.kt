package com.mercadolivro.customer

data class CustomerResponse (

    var id: Int? = null,

    var name: String,

    var email: String,

    var status: CustomerStatus
)
