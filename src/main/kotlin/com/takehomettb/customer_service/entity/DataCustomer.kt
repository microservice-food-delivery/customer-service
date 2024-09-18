package com.takehomettb.customer_service.entity

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ReturnStatus(
    var status: Boolean? = null,
    var data: Any? = null,
    var message: String? = null
){}

data class CustomerRequest(
    var name: String,
    var phone: String,
    var address: String,
)
