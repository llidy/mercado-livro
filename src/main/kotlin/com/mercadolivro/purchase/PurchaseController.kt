package com.mercadolivro.purchase

import com.mercadolivro.book.BookModel
import com.mercadolivro.book.BookRequest
import com.mercadolivro.book.BookService
import com.mercadolivro.customer.CustomerRequest
import com.mercadolivro.customer.toBook
import com.mercadolivro.customer.toCostumer
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("purchases")
class PurchaseController(
    private val purchaseService: PurchaseService,
    private val purchaseMapper: PurchaseMapper

) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun purchase(@RequestBody purchaseRequest: PurchaseRequest){
        purchaseService.create(purchaseMapper.toModel(purchaseRequest))
    }
}