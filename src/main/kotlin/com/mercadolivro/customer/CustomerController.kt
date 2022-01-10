package com.mercadolivro.customer

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customers")
class CustomerController (
    val customerService: CustomerService
) {


    @GetMapping
    fun getAll(@RequestParam name: String?): List<CustomerResponse> {
        return customerService.getAll(name).map { it.toResponse() }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody request: CustomerRequest) {
        customerService.createCustomer(request.toCostumer())
    }

    @GetMapping("/{id}")
    fun findById (@PathVariable id: Int): CustomerResponse {
       return customerService.findById(id).toResponse()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody requestPut: PutCustomerRequest){
        val customerSaved = customerService.findById(id)
        customerService.update(id, requestPut.toCostumer(customerSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int){
        customerService.delete(id)
    }


}