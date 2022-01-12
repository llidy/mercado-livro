package com.mercadolivro.customer

import com.mercadolivro.book.BookService
import com.mercadolivro.exception.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {

    fun getAll(name: String?, pageable: Pageable): Page<Customer> {
        name?.let {
            return customerRepository.findByNameContaining(it, pageable)
        }
        return customerRepository.findAll(pageable)
    }

    fun createCustomer(customer: Customer){
        customerRepository.save(customer)
    }

    fun findById(id: Int): Customer {
        return customerRepository.findById(id).orElseThrow{NotFoundException("Custemer ${id} not exists", "ML-0002")}
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