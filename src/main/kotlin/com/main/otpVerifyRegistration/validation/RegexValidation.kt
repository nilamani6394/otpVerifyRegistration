package com.main.otpVerifyRegistration.validation

import java.util.regex.Matcher
import java.util.regex.Pattern
import org.springframework.mail.SimpleMailMessage

import org.springframework.mail.javamail.JavaMailSender

import org.springframework.beans.factory.annotation.Autowired





class RegexValidation {

    //for email validation
    fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

    //for Name validation
    fun validateLetters(txt: String?): Boolean {

        val regx = "^[a-zA-Z\\s]+$"
        val pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(txt)
        return matcher.find()
    }
    fun isValidPassword(password: String?): Boolean {

        //check
        val regex = ("^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$")
        // Compile
        val p = Pattern.compile(regex)
        if (password == null) {
            return false
        }
        val m = p.matcher(password)

        return m.matches()
    }



   /* private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }*/

    /* val map=HashMap<String,String>()
        val existUser=userRepository.findByEmail(request.email)
        if(existUser?.email!=request.email){
            map["Email"] = "Email is incorrect"
        }
        if(existUser?.password != request.password){
            map["password"] = "Password is incorrect"
        }
        if(!map.isNullOrEmpty()){
            return ResponseEntity(ResError(map),HttpStatus.NOT_ACCEPTABLE)
        }*/
}
//validation for email
fun String.isValid(): Boolean {
    val emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$"
    val pat = Pattern.compile(emailRegex)
    return pat.matcher(this).matches()
}

//for password validation
fun String.isValidPassword(): Boolean {

    //check
    val regex = ("^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$")
    // Compile
    val p = Pattern.compile(regex)
    if (this == null) {
        return false
    }
    val m = p.matcher(this)

    return m.matches()
}

//for Name validation
fun String.validateLetters(): Boolean {

    val regx = "^[a-zA-Z\\s]+$"
    val pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE)
    val matcher: Matcher = pattern.matcher(this)
    return matcher.find()
}
