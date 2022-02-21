package com.mercadolivro.purchase

import com.mercadolivro.book.BookRepository
import com.mercadolivro.event.PurchaseEvent
import com.mercadolivro.helper.buildPurchase
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.context.ApplicationEventPublisher
import java.util.*

@ExtendWith(MockKExtension::class)
class PurchaseServiceTest {

    @MockK
    private lateinit var purchaseRepository: PurchaseRepository

    @MockK
    private lateinit var applicationEventPublisher: ApplicationEventPublisher

    @MockK
    private lateinit var bookRepository: BookRepository

    @InjectMockKs
    private lateinit var purchaseService: PurchaseService

    val purchaseEventSlot = slot<PurchaseEvent>()

    @Test
    fun `should create purchase and publish event`() {

        val fakePurchase = buildPurchase()

        every { purchaseRepository.save(fakePurchase) } returns fakePurchase

        every { applicationEventPublisher.publishEvent(any()) } just runs

        purchaseService.create(fakePurchase)

        verify(exactly = 1) { purchaseRepository.save(fakePurchase) }
        verify(exactly = 1) { applicationEventPublisher.publishEvent(capture(purchaseEventSlot)) }

        assertEquals(fakePurchase, purchaseEventSlot.captured.purchase)

    }

    @Test
    fun `should update purchase`() {
        val purchaseFake = buildPurchase()

        every { purchaseRepository.save(purchaseFake) } returns purchaseFake

        purchaseService.update(purchaseFake)

        verify(exactly = 1) { purchaseRepository.save(purchaseFake) }
    }
}