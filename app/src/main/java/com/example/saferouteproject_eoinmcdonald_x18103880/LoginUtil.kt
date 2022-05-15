package com.example.saferouteproject_eoinmcdonald_x18103880

object LoginUtil {

    private val existingUsers = listOf("Eoin" , "Peter")


    fun validLoginInput(
        email : String,
        password : String
    ) : Boolean {
        // write conditions along with their return statement
        // if username / password / confirm password are empty return false
        if (email.isEmpty()|| password.isEmpty()){
            return false
        }
        // if username exists in the existingUser list return false
        if (email in existingUsers){
            return false
        }
        // if password does not matches confirm password return false
        if (password != password){
            return false
        }
        // if digit count of the password is less than 2 return false
        if (password.count { it.isDigit() } < 2){
            return false
        }
        return true
    }
}