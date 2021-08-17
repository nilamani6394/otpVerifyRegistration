package com.main.otpVerifyRegistration.model.request

data class UserReq(
    val firstName:String="",
    val lastName:String="",
    val email:String="",
    val password:String="",
)
