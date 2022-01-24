package com.mercadolivro.event

import com.mercadolivro.book.BookRepository
import com.mercadolivro.book.BookService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

class UpdateSoldBookListener {
    @Component
    class GenerateNfeListener(
        private val bookService: BookService,

    ) {
        @Async
        @EventListener
        fun listen(purchaseEvent: PurchaseEvent) {

            bookService.purchace(purchaseEvent.purchase.books)

        }
    }
}