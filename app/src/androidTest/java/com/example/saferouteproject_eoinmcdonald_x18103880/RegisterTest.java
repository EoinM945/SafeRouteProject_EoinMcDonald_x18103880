package com.example.saferouteproject_eoinmcdonald_x18103880;

import android.view.View;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

import androidx.annotation.StringRes;
import androidx.test.espresso.ViewAction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

/**
 * Created by teerapong on 7/7/2016 AD.
 */
@RunWith(AndroidJUnit4.class)
public class RegisterTest {

    @Rule
    public ActivityTestRule<Register> activityTestRule = new ActivityTestRule<>(Register.class);

    @Test
    public void emailIsEmpty() {
        onView(withId(R.id.email)).perform(clearText());
        onView(withId(R.id.phone)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.fullname)).perform(typeText("test user"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(clearText());
        onView(withId(R.id.conPassword)).perform(clearText());
        onView(withId(R.id.registerBtn)).perform(click());
        onView(withId(R.id.email)).check(matches(withError(getString(R.string.error_field_required))));
    }

    @Test
    public void passwordIsEmpty() {
        onView(withId(R.id.email)).perform(typeText("incorrect@email.com"), closeSoftKeyboard());
        onView(withId(R.id.phone)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.fullname)).perform(typeText("test user"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(clearText());
        onView(withId(R.id.conPassword)).perform(clearText());
        onView(withId(R.id.registerBtn)).perform(click());
        onView(withId(R.id.password)).check(matches(withError(getString(R.string.error_field_required))));
    }



    @Test
    public void emailIsInvalid() {
        onView(withId(R.id.email)).perform(typeText("sample"), closeSoftKeyboard());
        onView(withId(R.id.phone)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.fullname)).perform(typeText("test user"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.conPassword)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.email)).check(matches(withError(getString(R.string.error_invalid_email))));
    }

    @Test
    public void passwordIsTooShort() {
        onView(withId(R.id.email)).perform(typeText("incorrect@email.com"), closeSoftKeyboard());
        onView(withId(R.id.phone)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.fullname)).perform(typeText("test user"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.conPassword)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.password)).check(matches(withError(getString(R.string.error_invalid_password))));
    }

    @Test
    public void RegisterFailed() {
        onView(withId(R.id.email)).perform(typeText("incorrect@email.com"), closeSoftKeyboard());
        onView(withId(R.id.phone)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.fullname)).perform(typeText("test user"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.conPassword)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.registerBtn)).perform(click());
        onView(withText(getString(R.string.error_login_failed)))
                .inRoot(withDecorView(not(activityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }



    @Test
    public void loginSuccessfully_shouldShowToast() {
        onView(withId(R.id.email)).perform(typeText("incorrect@email.com"), closeSoftKeyboard());
        onView(withId(R.id.phone)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.fullname)).perform(typeText("test user"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.conPassword)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.registerBtn)).perform(click());
        onView(withText(getString(R.string.registration_successfully)))
                .inRoot(withDecorView(not(activityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    private String getString(@StringRes int resourceId) {
        return activityTestRule.getActivity().getString(resourceId);
    }

    private static Matcher<View> withError(final String expected) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (item instanceof EditText) {
                    return ((EditText)item).getError().toString().equals(expected);
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Not found error message" + expected + ", find it!");
            }
        };
    }
}
