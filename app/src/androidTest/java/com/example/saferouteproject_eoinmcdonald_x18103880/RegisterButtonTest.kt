package com.example.saferouteproject_eoinmcdonald_x18103880

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegisterButtonTest {
    @get : Rule
    var mActivityRule = ActivityScenarioRule(Register::class.java)

    @Before
    fun setUp() {
        //initial setup code
    }

    @Test
    fun clickForRegister() {
        onView(withId(R.id.registerBtn)).perform(click())
    }

    @Test
    fun clickForLogin() {
        onView(withId(R.id.loginNowBtn)).perform(click())
    }

    @After
    fun tearDown() {
        //clean up code
    }
}