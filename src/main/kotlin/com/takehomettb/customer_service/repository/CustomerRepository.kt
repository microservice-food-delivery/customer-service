package com.takehomettb.customer_service.repository

import com.takehomettb.customer_service.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Int> {
    fun findCustomerByCustomerId(id: Int): Customer?
}