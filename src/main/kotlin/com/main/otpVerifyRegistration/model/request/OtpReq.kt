package com.main.otpVerifyRegistration.model.request

data class OtpReq(
    val email:String="",
    val otp:Int,
)
