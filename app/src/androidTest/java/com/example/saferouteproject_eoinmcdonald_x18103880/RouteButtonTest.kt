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
class RouteButtonTest {
    @get : Rule
    var mActivityRule = ActivityScenarioRule(MapsActivity::class.java)

    @Before
    fun setUp() {
        //initial setup code
    }

    @Test
    fun clickForRoute() {
        onView(withId(R.id.B_search)).perform(click())

    }

    @Test
    fun clickForCancel(){
        onView(withId(R.id.B_clear)).perform(click())
    }

    @Test
    fun clickForStartRoute(){
        onView(withId(R.id.start_route_btn)).perform(click())
    }

    @Test
    fun clickForEndRoute(){
        onView(withId(R.id.end_route_btn)).perform(click())
    }

    @After
    fun tearDown() {
        //clean up code
    }
}