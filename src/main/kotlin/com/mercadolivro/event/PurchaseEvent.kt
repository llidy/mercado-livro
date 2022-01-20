package com.mercadolivro.event

import com.mercadolivro.purchase.Purchase
import org.springframework.context.ApplicationEvent

class PurchaseEvent(
    source: Any,
    val purchase: Purchase
): ApplicationEvent(source)