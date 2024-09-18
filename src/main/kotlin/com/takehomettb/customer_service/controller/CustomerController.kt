package com.takehomettb.customer_service.controller

import com.takehomettb.customer_service.entity.Customer
import com.takehomettb.customer_service.entity.CustomerRequest
import com.takehomettb.customer_service.entity.ReturnStatus
import com.takehomettb.customer_service.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/customers")
class CustomerController {
    @Autowired lateinit var customerService: CustomerService

    @GetMapping("/getCustomers")
    fun getCustomers(): ResponseEntity<List<Customer>> {
        return ResponseEntity.ok().body(customerService.getAll())
    }

    @GetMapping("/getCustomer/{customerId}")
    fun getCustomerById(@PathVariable customerId: Int): ResponseEntity<Any> {
        return ResponseEntity.ok().body(customerService.getCustomerById(customerId))
    }

    @PostMapping("/createCustomer")
    fun createCustomer(@RequestBody customer: Customer): ResponseEntity<ReturnStatus> {
        return ResponseEntity.ok().body(customerService.createCustomer(customer))
    }

    @PutMapping("/updateCustomer/{customerId}")
    fun updateCustomer(@PathVariable customerId: Int, @RequestBody customer: Customer): ResponseEntity<ReturnStatus> {
        return ResponseEntity.ok().body(customerService.updateCustomer(customerId,customer))
    }
}