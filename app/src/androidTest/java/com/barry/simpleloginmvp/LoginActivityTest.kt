package com.barry.simpleloginmvp

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import barry.com.simpleloginmvp.R
import com.barry.simpleloginmvp.login.LoginActivity
import org.hamcrest.Matchers.not
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters


@RunWith(AndroidJUnit4::class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class LoginActivityTest {

    companion object {
        const val INPUT_USERNAME = "Please input Username"
        const val INPUT_PASSWORD = "Please input Password"
        const val INPUT_VALID_PASSWORD = "Please input valid Password"
        const val INCORRECT_CREDENTIALS = "Incorrect Username or Password"

        const val INVALID_PASSWORD = "1234567"
        const val INCORRECT_USERNAME = "barry"
        const val INCORRECT_PASSWORD = "12345678"

        const val CORRECT_USERNAME = "barryjohn"
        const val CORRECT_PASSWORD = "qwerty123"
    }

    @get:Rule
    var activityRule = ActivityTestRule(LoginActivity::class.java)

    @Before
    fun setUp() {
        //Before Test case execution
    }

    @After
    fun tearDown() {
        //After Test case Execution
    }

    @Test
    fun showErrorUsername_EmptyUsername() {
        onView(withId(R.id.buttonLogin)).perform(click())

        onView(withId(R.id.editTextUsername)).check(matches(hasErrorText(INPUT_USERNAME)))
        onView(hasErrorText(INPUT_USERNAME)).check(matches(isDisplayed()))
    }

    @Test
    fun showErrorPassword_ValidUsernameEmptyPassword() {
        onView(withId(R.id.editTextUsername)).perform(typeText(INCORRECT_USERNAME), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.buttonLogin)).perform(click())

        onView(withId(R.id.editTextPassword)).check(matches(hasErrorText(INPUT_PASSWORD)))
        onView(hasErrorText(INPUT_PASSWORD)).check(matches(isDisplayed()))
    }

    @Test
    fun showErrorPassword_ValidUsernameInvalidPassword() {
        onView(withId(R.id.editTextUsername)).perform(typeText(INCORRECT_USERNAME))
        onView(withId(R.id.editTextPassword)).perform(typeText(INVALID_PASSWORD), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.buttonLogin)).perform(click())

        onView(withId(R.id.editTextPassword)).check(matches(hasErrorText(INPUT_VALID_PASSWORD)))
        onView(hasErrorText(INPUT_VALID_PASSWORD)).check(matches(isDisplayed()))
    }

    @Test
    fun showErrorDialog_IncorrectUsernameIncorrectPassword() {
        onView(withId(R.id.editTextUsername)).perform(typeText(INCORRECT_USERNAME))
        onView(withId(R.id.editTextPassword)).perform(typeText(INCORRECT_PASSWORD), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.buttonLogin)).perform(click())

        onView(withText(INCORRECT_CREDENTIALS)).inRoot(withDecorView(not(activityRule.activity.window.decorView)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun showErrorDialog_CorrectUsernameIncorrectPassword() {
        onView(withId(R.id.editTextUsername)).perform(typeText(CORRECT_USERNAME))
        onView(withId(R.id.editTextPassword)).perform(typeText(INCORRECT_PASSWORD), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.buttonLogin)).perform(click())

        onView(withText(INCORRECT_CREDENTIALS)).inRoot(withDecorView(not(activityRule.activity.window.decorView)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun showErrorDialog_IncorrectUsernameCorrectPassword() {
        onView(withId(R.id.editTextUsername)).perform(typeText(INCORRECT_USERNAME))
        onView(withId(R.id.editTextPassword)).perform(typeText(CORRECT_PASSWORD), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.buttonLogin)).perform(click())

        onView(withText(INCORRECT_CREDENTIALS)).inRoot(withDecorView(not(activityRule.activity.window.decorView)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun goToMain_CorrectUsernameCorrectPassword() {
        Intents.init()

        onView(withId(R.id.editTextUsername)).perform(typeText(CORRECT_USERNAME))
        onView(withId(R.id.editTextPassword)).perform(typeText(CORRECT_PASSWORD), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.buttonLogin)).perform(click())

        intended(hasComponent(MainActivity::class.java.name))

        Intents.release()
    }
}