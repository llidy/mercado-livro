package com.mercadolivro.customer

import com.mercadolivro.book.BookService
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {

    fun getAll(name: String?): List<Customer> {
        name?.let {
            return customerRepository.findByNameContaining(it)
        }
        return customerRepository.findAll().toList()
    }

    fun createCustomer(customer: Customer){
        customerRepository.save(customer)
    }

    fun findById(id: Int): Customer {
        return customerRepository.findById(id).orElseThrow()
    }

    fun update(id: Int, customer: Customer){
        if(!customerRepository.existsById(customer.id!!)){
            throw Exception()
        }

       customerRepository.save(customer)
    }

    fun delete(id: Int){
        val customer = findById(id)
        bookService.deleteByCustomer(customer)

        customer.status = CustomerStatus.INACTIVE
        customerRepository.save(customer)
    }
}