package com.mercadolivro.customer

import com.mercadolivro.book.BookService
import com.mercadolivro.book.PageResponse
import io.mockk.InternalPlatformDsl.toArray
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*
import javax.print.attribute.standard.PageRanges

@ExtendWith(MockKExtension::class)
class CustomerServiceTest{

    @MockK
    private lateinit var customerRepository: CustomerRepository

    @MockK
    private lateinit var bookService: BookService

    @MockK
    private lateinit var bCrypt: BCryptPasswordEncoder

    @InjectMockKs
    private lateinit var customerService: CustomerService

    @Test
    fun `should return all customers`() {
        val fakeCustomer = listOf(buildCustomer(), buildCustomer())

        every { customerRepository.findAll() } returns fakeCustomer

        val customers = customerService.getAll(name = null)

        assertEquals(fakeCustomer, customers)
        verify (exactly = 1) { customerRepository.findAll() }
        verify (exactly = 0) { customerRepository.findByNameContaining(any())}
    }

    @Test
    fun `should return customers when name is informed`() {
        val name = Math.random().toString()

        val fakeCustomer = listOf(buildCustomer(), buildCustomer())

        every { customerRepository.findByNameContaining(name) } returns fakeCustomer

        val customers = customerService.getAll(name)

        assertEquals(fakeCustomer, customers)
        verify (exactly = 0) { customerRepository.findAll() }
        verify (exactly = 1) { customerRepository.findByNameContaining(name)}
    }

    fun buildCustomer(
        id: Int? = null,
        name: String = "customer name",
        email: String= "${UUID.randomUUID()}@email.com",
        password: String = "password"
    ) = Customer(
        id = id,
        name = name,
        email = email,
        password = password,
        status = CustomerStatus.ACTIVE,
        roles = setOf(Role.CUSTOMER)
    )

}


