package com.barry.simpleloginmvp.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import barry.com.simpleloginmvp.R
import com.barry.simpleloginmvp.BaseActivity
import com.barry.simpleloginmvp.MainActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity(), LoginContract.View {

    private lateinit var loginPresenterImpl: LoginPresenterImpl

    override fun onStart() {
        super.onStart()

        loginPresenterImpl = LoginPresenterImpl(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupViews()
    }

    override fun setupViews() {
        buttonLogin.setOnClickListener {
            loginPresenterImpl.validateCredentials(editTextUsername.text.toString(), editTextPassword.text.toString())
        }
    }

    override fun showErrorUsername(errorMsg: String) {
        editTextUsername.error = errorMsg
    }

    override fun showErrorPassword(errorMsg: String) {
        editTextPassword.error = errorMsg
    }

    override fun showErrorDialog(errorMsg: String) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
    }

    override fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}
