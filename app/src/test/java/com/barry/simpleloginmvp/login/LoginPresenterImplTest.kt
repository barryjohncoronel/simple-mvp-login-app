package com.barry.simpleloginmvp.login

import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class LoginPresenterImplTest {

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

    private lateinit var loginPresenterImpl: LoginPresenterImpl

    @Mock
    private lateinit var loginView: LoginContract.View

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        loginPresenterImpl = LoginPresenterImpl(loginView)
    }

    @After
    fun tearDown() {
        //After Test case Execution
    }

    @Test
    fun validateCredentials_EmptyUsername_throwError() {
        loginPresenterImpl.validateCredentials("", anyString())

        verify(loginView).showErrorUsername(INPUT_USERNAME)
    }

    @Test
    fun validateCredentials_ValidUsernameEmptyPassword_throwError() {
        loginPresenterImpl.validateCredentials(INCORRECT_USERNAME, "")

        verify(loginView).showErrorPassword(INPUT_PASSWORD)
    }

    @Test
    fun validateCredentials_ValidUsernameInvalidPassword_throwError() {
        loginPresenterImpl.validateCredentials(INCORRECT_USERNAME, INVALID_PASSWORD)

        verify(loginView).showErrorPassword(INPUT_VALID_PASSWORD)
    }

    @Test
    fun validateCredentials_IncorrectUsernameIncorrectPassword_throwError() {
        loginPresenterImpl.validateCredentials(INCORRECT_USERNAME, INCORRECT_PASSWORD)

        verify(loginView).showErrorDialog(INCORRECT_CREDENTIALS)
    }

    @Test
    fun validateCredentials_CorrectUsernameIncorrectPassword_throwError() {
        loginPresenterImpl.validateCredentials(CORRECT_USERNAME, INCORRECT_PASSWORD)

        verify(loginView).showErrorDialog(INCORRECT_CREDENTIALS)
    }

    @Test
    fun validateCredentials_IncorrectUsernameCorrectPassword_throwError() {
        loginPresenterImpl.validateCredentials(INCORRECT_USERNAME, CORRECT_PASSWORD)

        verify(loginView).showErrorDialog(INCORRECT_CREDENTIALS)
    }

    @Test
    fun validateCredentials_CorrectUsernameCorrectPassword_Success() {
        loginPresenterImpl.validateCredentials(CORRECT_USERNAME, CORRECT_PASSWORD)

        verify(loginView, never()).showErrorUsername(anyString())
        verify(loginView, never()).showErrorPassword(anyString())
        verify(loginView, never()).showErrorDialog(anyString())
    }
}