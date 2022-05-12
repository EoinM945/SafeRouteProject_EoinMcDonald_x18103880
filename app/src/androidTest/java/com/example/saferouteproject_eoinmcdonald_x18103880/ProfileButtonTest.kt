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
class ProfileButtonTest {
    @get : Rule
    var mActivityRule = ActivityScenarioRule(UserProfile::class.java)

    @Before
    fun setUp() {
        //initial setup code
    }

    @Test
    fun clickForRoute() {
        onView(withId(R.id.route_button)).perform(click())

    }

    @Test
    fun clickForUploadImage(){
        onView(withId(R.id.image_button)).perform(click())
    }

    @Test
    fun clickForLogout(){
        onView(withId(R.id.logout_button)).perform(click())
    }

    @After
    fun tearDown() {
        //clean up code
    }
}