package com.takehomettb.customer_service.service

import com.takehomettb.customer_service.entity.Customer
import com.takehomettb.customer_service.entity.ReturnStatus
import com.takehomettb.customer_service.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class CustomerService {
    @Autowired lateinit var customerRepository: CustomerRepository

    fun getAll(): List<Customer> {
        return customerRepository.findAll()
    }

    fun getCustomerById(id: Int): Any {
        val customer: Customer = customerRepository.findById(id).orElse(null)
            ?: return ReturnStatus(
                status = false,
                message = "ไม่มีหมายเลขผู้ใช้รายนี้"
            )
        return customer
    }

    fun createCustomer(customer: Customer): ReturnStatus {
        if (customer.name.isNullOrEmpty()) {
            return ReturnStatus(status = false, message = "กรุณากรอกชื่อผู้ใช้")
        }
        if (customer.address.isNullOrEmpty()) {
            return ReturnStatus(status = false, message = "กรุณากรอกที่อยู่")
        }
        if (customer.phone.isNullOrEmpty()) {
            return ReturnStatus(status = false, message = "กรุณากรอกเบอร์โทรศัพท์")
        }

        try {
           val result = customerRepository.save(customer)
            return ReturnStatus(status = true, data = result, message = "เพิ่มผู้ใช้เสร็จสิ้น")
        } catch (e: Exception) {
            return ReturnStatus(status = false, message = "เกิดข้อผิดพลาด: ${e.message}")
        }
    }

    fun updateCustomer(id: Int, customer: Customer): ReturnStatus {
        var result = customerRepository.findById(id).orElse(null)
            ?: return ReturnStatus(
                status = false,
                message = "ไม่มีหมายเลขผู้ใช้นี้"
            )

        if(!customer.name.isNullOrEmpty()) {
            result.name = customer.name
        }
        if(!customer.address.isNullOrEmpty()) {
            result.address = customer.address
        }
        if(!customer.phone.isNullOrEmpty()) {
            result.phone = customer.phone
        }
        try {
            result.updatedAt = LocalDateTime.now()
            result = customerRepository.save(result)
            return ReturnStatus(
                status = true,
                data = result,
                message = "อัปเดตผู้ใช้เรียบร้อยแล้ว"
            )
        }catch (e: Exception) {
            return ReturnStatus(
                status = false,
                message = "มีข้อผิดพลาดเกิดขึ้น"
            )
        }
    }


}