package com.takehomettb.customer_service.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "customer")
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    var customerId: Int? = null,

    @Column(name = "name")
    var name: String? = null,

    @Column(name = "address")
    var address: String? = null,

    @Column(name = "phone")
    var phone: String? = null,

    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = LocalDateTime.now(),

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = LocalDateTime.now(),
)