package com.barry.simpleloginmvp.login

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import barry.com.simpleloginmvp.R
import com.barry.simpleloginmvp.MainActivity
import junit.framework.Assert.assertTrue
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.shadows.ShadowToast

@RunWith(RobolectricTestRunner::class)
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

    private lateinit var loginActivity: LoginActivity
    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button

    @Before
    fun setup() {
        loginActivity = Robolectric.setupActivity(LoginActivity::class.java)
        editTextUsername = loginActivity.findViewById(R.id.editTextUsername)
        editTextPassword = loginActivity.findViewById(R.id.editTextPassword)
        buttonLogin = loginActivity.findViewById(R.id.buttonLogin)
    }

    @After
    fun tearDown() {
        //After Test case Execution
    }

    @Test
    fun showErrorUsername_EmptyUsername() {
        editTextUsername.setText("")
        editTextPassword.setText("")

        buttonLogin.performClick()

        assertThat(editTextUsername.error.toString(), `is`(INPUT_USERNAME))
    }

    @Test
    fun showErrorPassword_ValidUsernameEmptyPassword() {
        editTextUsername.setText(INCORRECT_USERNAME)
        editTextPassword.setText("")

        buttonLogin.performClick()

        assertThat(editTextPassword.error.toString(), `is`(INPUT_PASSWORD))
    }

    @Test
    fun showErrorPassword_ValidUsernameInvalidPassword() {
        editTextUsername.setText(INCORRECT_USERNAME)
        editTextPassword.setText(INVALID_PASSWORD)

        buttonLogin.performClick()

        assertThat(editTextPassword.error.toString(), `is`(INPUT_VALID_PASSWORD))
    }

    @Test
    fun showErrorDialog_IncorrectUsernameIncorrectPassword() {
        editTextUsername.setText(INCORRECT_USERNAME)
        editTextPassword.setText(INCORRECT_PASSWORD)

        buttonLogin.performClick()

        assertThat(ShadowToast.getTextOfLatestToast(), `is`(INCORRECT_CREDENTIALS))
    }

    @Test
    fun showErrorDialog_CorrectUsernameIncorrectPassword() {
        editTextUsername.setText(CORRECT_USERNAME)
        editTextPassword.setText(INCORRECT_PASSWORD)

        buttonLogin.performClick()

        assertThat(ShadowToast.getTextOfLatestToast(), `is`(INCORRECT_CREDENTIALS))
    }

    @Test
    fun showErrorDialog_IncorrectUsernameCorrectPassword() {
        editTextUsername.setText(INCORRECT_USERNAME)
        editTextPassword.setText(CORRECT_PASSWORD)

        buttonLogin.performClick()

        assertThat(ShadowToast.getTextOfLatestToast(), `is`(INCORRECT_CREDENTIALS))
    }

    @Test
    fun goToMain_CorrectUsernameCorrectPassword() {
        editTextUsername.setText(CORRECT_USERNAME)
        editTextPassword.setText(CORRECT_PASSWORD)

        buttonLogin.performClick()

        val expectedIntent = Intent(loginActivity, MainActivity::class.java)
        val shadowActivity = Shadows.shadowOf(loginActivity)
        val actualIntent = shadowActivity.nextStartedActivity

        assertTrue(actualIntent.filterEquals(expectedIntent))
    }

}

