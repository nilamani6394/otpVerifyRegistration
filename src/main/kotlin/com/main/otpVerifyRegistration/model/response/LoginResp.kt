package com.main.otpVerifyRegistration.model.response

data class LoginResp(
    val id:Long,
    val firstName:String="",
    val lastName:String="",
    val email:String="",
)
