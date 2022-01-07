package com.mercadolivro.customer

import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository
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

    fun getById(id: Int): Customer {
        return customerRepository.findById(id).orElseThrow()
    }

    fun update(id: Int, customer: Customer){
        if(!customerRepository.existsById(customer.id!!)){
            throw Exception()
        }

       customerRepository.save(customer)
    }

    fun delete(id: Int){
        if(!customerRepository.existsById(id)){
            throw Exception()
        }

        customerRepository.deleteById(id)
    }
}