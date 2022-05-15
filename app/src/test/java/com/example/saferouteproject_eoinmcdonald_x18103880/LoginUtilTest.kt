package com.example.saferouteproject_eoinmcdonald_x18103880

import com.google.common.truth.Truth
import org.junit.Test

class LoginUtilTest {

    // Write tests for the RegistrationUtil class considering all the conditions
    // annotate each function with @Test
    // We can use backtick to write function name..
    // whatever we write in those backtick will be considered as function name
    @Test
    fun `empty email returns false`(){
        // Pass the value to the function of RegistrationUtil class
        // since RegistrationUtil is an object/ singleton we do not need to create its object
        val result = LoginUtil.validLoginInput(
            "",
            "123"
        )
        // assertThat() comes from the truth library that we added earlier
        // put result in it and assign the boolean that it should return
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`(){
        // Pass the value to the function of RegistrationUtil class
        // since RegistrationUtil is an object/ singleton we do not need to create its object
        val result = LoginUtil.validLoginInput(
            "eoin@gmail.com",
            ""
        )
        // assertThat() comes from the truth library that we added earlier
        // put result in it and assign the boolean that it should return
        Truth.assertThat(result).isFalse()
    }

    // follow the same for other cases also
    // in this test if username and correctly repeated password returns true
    @Test
    fun `email and correctly repeated password returns true`() {
        val result = LoginUtil.validLoginInput(
            "eoin@gmail.com",
            "123"

        )
        Truth.assertThat(result).isTrue()
    }

    // in this test userName already taken returns false
    @Test
    fun `incorrect email returns false`() {
        val result = LoginUtil.validLoginInput(
            "eoin2666@gmail.com",
            "eoin"

        )
        Truth.assertThat(result).isFalse()
    }

    // if confirm password does nt matches the password return false
    @Test
    fun `incorrect password returns false`() {
        val result = LoginUtil.validLoginInput(
            "eoin@gmail.com",
            "eoin9999"

        )
        Truth.assertThat(result).isFalse()
    }

    // in this test if password has less than two digits than return false
    @Test
    fun `less than two digit password return false`() {
        val result = LoginUtil.validLoginInput(
            "eoin@gmail.com",
            "eoin"

        )
        Truth.assertThat(result).isFalse()
    }
}