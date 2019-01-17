package com.barry.simpleloginmvp.login

class LoginContract {

    interface View {
        fun showErrorUsername(errorMsg: String)
        fun showErrorPassword(errorMsg: String)
        fun showErrorDialog(errorMsg: String)
        fun goToMain()
    }

    interface Presenter {
        fun validateCredentials(username: String, password: String)
    }
}