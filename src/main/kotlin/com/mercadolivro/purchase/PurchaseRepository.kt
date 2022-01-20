package com.mercadolivro.purchase

import org.springframework.data.jpa.repository.JpaRepository

interface PurchaseRepository: JpaRepository<Purchase, Long> {

}
