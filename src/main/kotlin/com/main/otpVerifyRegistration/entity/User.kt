package com.main.otpVerifyRegistration.entity

import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    val id:Long=-1,
    @NotBlank(message = "Name cannot be blank")
    val firstName:String="",
    @NotBlank(message = "last name cannot be blank")
    val lastName:String="",
    @NotBlank(message = "email cannot be blank")
    @Email(message = "plz provide a valid message")
    @Column(name = "email",unique = true)
    val email:String="",
    var password:String="",
    var isEnabled: Boolean=false,
    /** it will be isForgotPasswordVerified */
    var isFPVerified: Boolean=false,
    /** for change password */
    val isPasswordMatched: Boolean=false,
    var otp: Int =0
)
