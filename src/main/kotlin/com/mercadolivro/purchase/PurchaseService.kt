package com.mercadolivro.purchase

import org.springframework.stereotype.Service

@Service
class PurchaseService(
    private val purchaseRepository: PurchaseRepository
) {

    fun create(purchase: Purchase){
        purchaseRepository.save(purchase)
    }
}
