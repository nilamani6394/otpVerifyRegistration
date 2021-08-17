package com.main.otpVerifyRegistration.repo

import com.main.otpVerifyRegistration.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.web.bind.annotation.RequestParam
import javax.transaction.Transactional


interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.isEnabled = :isEnabled where u.email = :email")
    fun setVerified(@RequestParam("isEnabled") isEnabled: Boolean, @RequestParam("email") email: String)

    @Transactional
    @Modifying
    @Query(value = "UPDATE User u SET u.password= :password where u.email= :email")
    fun resetPassword(@RequestParam(value = "password") password: String,@RequestParam(value = "email") email:String)


    @Transactional
    @Modifying
    @Query(value = "UPDATE User u SET u.isFPVerified= :isFPVerified where u.email= :email")
    fun setForgotPasswordVerified(@RequestParam(value = "isFPVerified") isFPVerified: Boolean,@RequestParam(value = "email") email:String)

    @Transactional
    @Modifying
    @Query(value = "UPDATE User u SET u.isPasswordMatched= :isPasswordMatched where u.email= :email")
    fun setChangePassword(@RequestParam(value = "isPasswordMatched") isPasswordMatched: Boolean, @RequestParam(value="email") email: String)
}