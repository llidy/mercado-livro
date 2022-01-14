package com.mercadolivro.customer

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

class PutCustomerRequest (

    @field:NotEmpty(message = "O nome deve ser informado")
    var name: String,

    @Email(message = "E-mail deve ser válido")
    var email: String
)