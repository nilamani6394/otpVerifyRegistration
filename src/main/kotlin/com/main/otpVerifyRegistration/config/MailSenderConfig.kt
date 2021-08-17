package com.main.otpVerifyRegistration.config

/*import java.io.IOException
import java.util.*
import java.net.PasswordAuthentication
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.internet.AddressException
import javax.mail.internet.MimeMessage
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart


class MailSenderConfig{
    @Throws(AddressException::class, MessagingException::class, IOException::class)
    fun sendEmail(){
        val prop=Properties()
        prop["email.smtp.auth"] = "true"
        prop["email.smtp.starttls.enable"] = "true"
        prop["email.smtp.host"] = "smtp.gmail.com"
        prop["email.smtp.port"] = "587"

        val session: Session = Session.getInstance(prop, object : Authenticator() {
            protected val passwordAuthentication: PasswordAuthentication?
                protected get() = PasswordAuthentication("zennil1305014666@gmail.com",'Nilmani@9')
        })
        val msg:Message=MimeMessage(session)
        msg.setFrom(InternetAddress("zennil1305014666@gmail.com", false))

        msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse("zennil1305014666@gmail.com"))
        msg.subject = "About otp verification"
        msg.setContent("plz verify the otp","text/html")

        val msgBodyPart=MimeBodyPart()
        msgBodyPart.setContent("otp verification","text/html")

    }

}*/
