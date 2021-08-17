package com.main.otpVerifyRegistration.controller

import com.main.otpVerifyRegistration.entity.User
import com.main.otpVerifyRegistration.model.request.*
import com.main.otpVerifyRegistration.model.response.*
import com.main.otpVerifyRegistration.repo.UserRepository
import com.main.otpVerifyRegistration.validation.isValid
import com.main.otpVerifyRegistration.validation.isValidPassword
import com.main.otpVerifyRegistration.validation.validateLetters
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import kotlin.random.Random
import org.springframework.http.ResponseEntity as ResponseEntity1

@RestController
@RequestMapping("/user")
class UserController {
    @Autowired
    private lateinit var userRepository: UserRepository

    @PostMapping("/signUp")
    fun createUser(@ModelAttribute request: UserReq): ResponseEntity1<*> {
        val map = HashMap<String, String>()
        if (!request.firstName.validateLetters()) {
            map["firstName"] = "FirstName cannot be empty"
        }
        if (!request.lastName.validateLetters()) {
            map["lastName"] = "lastName cannot be empty"
        }
        if (!request.email.isValid()) {
            map["email"] = "enter a valid email id"
        }
        if (request.password.isValidPassword()) {
            map["password"] = "PassWord cannot be empty"
        }
        if (!map.isNullOrEmpty()) {
            return ResponseEntity1(ResError(map), HttpStatus.NOT_ACCEPTABLE)
        }

        val newUser = User(
            firstName = request.firstName, lastName = request.lastName, email = request.email,
            password = request.password, otp = Random.nextInt(1111, 9999))
        userRepository.save(newUser)

        val resUser = UserRes(newUser.firstName, newUser.lastName, newUser.email)
        return ResponseEntity1(resUser, HttpStatus.OK)
    }

    /*  fun sendEmail():ResponseEntity<*>{
          sendEmail()
         return ResponseEntity(ResMessage("Otp sent to your email "),HttpStatus.OK)
      }*/

    @PatchMapping("/verifyUser")
    fun userVerify(@ModelAttribute request: OtpReq): ResponseEntity1<*> {
        val emailSearch = userRepository.findByEmail(request.email)
        return if (emailSearch?.otp == request.otp) {
            userRepository.setVerified(true, request.email)

            /*val userRep = ResOtp(emailSearch.id, emailSearch.firstName, emailSearch.lastName,
                    emailSearch.email, emailSearch.password, true)*/
            ResponseEntity1(ResMessage("You are verified user"), HttpStatus.OK)
        } else {
            ResponseEntity1(ResMessage("You are not a authorized user"), HttpStatus.NOT_ACCEPTABLE)
        }
    }

    @PostMapping("/loginUser")
    fun loginUser(@ModelAttribute request: LoginReq): ResponseEntity1<*> {
        val existUser = userRepository.findByEmail(request.email)
        val map = HashMap<String, String>()
        if (existUser?.email != request.email) {
            map["email"] = "Email is incorrect"
        }
        if (existUser?.password != request.password) {
            map["password"] = "Password is incorrect"
        }
        if (!map.isNullOrEmpty()) {
            return ResponseEntity1(ResError(map), HttpStatus.NOT_ACCEPTABLE)
        }
        return if (existUser !=null ) {
            val userResp = LoginResp(existUser.id, existUser.firstName, existUser.lastName, existUser.email)
            ResponseEntity1(userResp, HttpStatus.OK)
        } else {
            ResponseEntity1(ResMessage("You are not Registered"), HttpStatus.BAD_REQUEST)
        }

    }

    @PostMapping("/forgotPassword")
    fun forgotPassword(@ModelAttribute request: ReqForgot): ResponseEntity1<*> {
       // val emailSendService = EmailService()
        val existUser = userRepository.findByEmail(request.email)
        return if (existUser?.isEnabled == true) {
            // val token=OtpReq()

            ResponseEntity1(ResMessage("otp send to your email"), HttpStatus.OK)
        } else {
            ResponseEntity1(ResMessage("user is not verified yet. please register first"), HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/verifyForgotPasswordOtp")
    fun verifyForgotPasswordOtp(@ModelAttribute request: ResetRequest): ResponseEntity1<*> {
        val exUser = userRepository.findByEmail(request.email)

        return if (exUser == null) {
            ResponseEntity1(ResMessage("user is not exists"), HttpStatus.NOT_FOUND)
        } else {
            if (request.otp == exUser.otp) {
                userRepository.setForgotPasswordVerified(true,request.email)
                ResponseEntity1(ResMessage("Otp verified successfully.!"), HttpStatus.OK)
            } else {
                ResponseEntity1(ResMessage("Invalid otp"), HttpStatus.NOT_ACCEPTABLE)
            }
        }
    }

    @PostMapping("/resetPassword")
    fun resetPassword(@ModelAttribute request: UpdatePassword): ResponseEntity1<*> {

        val curUser = userRepository.findByEmail(request.email)
        return if (curUser?.isFPVerified == true) {
            userRepository.resetPassword(request.password, request.email)
            ResponseEntity1(ResMessage("password reset successfully"), HttpStatus.OK)
        } else {
            ResponseEntity1(ResMessage("you are not a verified user"), HttpStatus.NOT_ACCEPTABLE)
        }
    }

    /*@PostMapping("/verifyChangePassword")
    fun verifyPassword(@ModelAttribute request:ReqChangePassword): ResponseEntity1<*> {
        val curUser=  userRepository.findByEmail(request.email)

        return if(curUser == null){
            ResponseEntity1(ResMessage("user id not available"),HttpStatus.NOT_FOUND)
        }else{
            if(curUser.password == request.password){
                userRepository.setChangePassword(true,request.email)
                ResponseEntity1(SuccessMessage("Now you can change your password"),HttpStatus.OK)
            }else{
                ResponseEntity1(ResMessage("password mismatch"),HttpStatus.FORBIDDEN)
            }

        }
    }*/
  /*  @PostMapping("/resetChangePassword")
    fun changePassword(@ModelAttribute request:UpdatePassword): ResponseEntity1<*> {
        val curUser=userRepository.findByEmail(request.email)
        if(curUser != null){
            return if(curUser.password ==request.password){
                userRepository.resetPassword(request.password,request.email)
                ResponseEntity1(SuccessMessage("password change success"),HttpStatus.OK)
            }else{
                ResponseEntity1(ResMessage("Your  password not match with old password"),HttpStatus.FORBIDDEN)
            }
        }

    }*/
}
