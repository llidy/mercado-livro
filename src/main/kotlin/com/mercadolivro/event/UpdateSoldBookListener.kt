package com.mercadolivro.event

import com.mercadolivro.book.BookService
import com.mercadolivro.purchase.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.util.*

class UpdateSoldBookListener {
    @Component
    class GenerateNfeListener(
        private val bookService: BookService
    ) {

        @EventListener
        fun listen(purchaseEvent: PurchaseEvent) {

            bookService.purchace(purchaseEvent.purchase.books)
        }
    }
}