package com.mercadolivro.customer

import com.mercadolivro.validation.EmailAvailable
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class CustomerRequest (

    @field:NotEmpty(message = "O nome deve ser informado")
    var name: String,

    @field:Email(message = "E-mail deve ser válido")
    @field:NotEmpty
    @EmailAvailable
    var email: String
){

}

