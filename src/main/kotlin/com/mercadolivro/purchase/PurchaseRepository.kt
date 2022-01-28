package com.mercadolivro.purchase

import com.mercadolivro.book.BookStatus
import org.springframework.data.jpa.repository.JpaRepository

interface PurchaseRepository: JpaRepository<Purchase, Long> {


}
