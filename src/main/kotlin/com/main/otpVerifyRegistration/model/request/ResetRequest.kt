package com.main.otpVerifyRegistration.model.request

data class ResetRequest(
    val email:String="",
    val otp:Int,
)
