package com.mercadolivro.customer

import com.mercadolivro.security.UserCanOnlyAccessTheirOwnResource
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("customers")
class CustomerController (
    private val customerService: CustomerService
) {


    @GetMapping
    fun getAll(@RequestParam name: String?): List<CustomerResponse> {
        return customerService.getAll(name).map { it.toResponse() }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody @Valid request: CustomerRequest) {
        customerService.createCustomer(request.toCostumer())
    }

    @GetMapping("/{id}")
    @UserCanOnlyAccessTheirOwnResource
    fun findById (@PathVariable id: Int): CustomerResponse {
       return customerService.findById(id).toResponse()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody @Valid requestPut: PutCustomerRequest){
        val customerSaved = customerService.findById(id)
        customerService.update(id, requestPut.toCostumer(customerSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int){
        customerService.delete(id)
    }


}