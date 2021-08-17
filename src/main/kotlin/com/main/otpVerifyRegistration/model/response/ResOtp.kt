package com.main.otpVerifyRegistration.model.response

data class ResOtp(
    val id:Long,
    val firstName:String,
    val lastName:String,
    val email:String,
    val password:String,
    val isEnabled:Boolean,
)
