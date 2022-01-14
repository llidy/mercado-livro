package com.mercadolivro.exception

enum class Errors(val code: String, val message: String) {
    //Errors 001-099 referentes a requesições inválidas
    ML001("ML-001","Invalid Request"),

    //Errors 100-199 referentes a book
    ML100("ML-0001", "Book [%s] not exists"),
    ML101("ML-0002", "Cannot update book with status[%s]"),

    //Errors 200-299 referentes a Customer
    ML200("ML-0002", "Customer [%s] not exists")
}