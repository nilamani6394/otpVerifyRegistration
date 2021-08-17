package com.main.otpVerifyRegistration.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service("emailSendService")
class EmailService {
    private var javaMailSender: JavaMailSender? = null

    @Autowired
    fun EmailSenderService(javaMailSender: JavaMailSender?) {
        this.javaMailSender = javaMailSender
    }

    @Async
    fun sendEmail(email: SimpleMailMessage?) {
        if (email != null) {
            javaMailSender?.send(email)
        }
    }
}
