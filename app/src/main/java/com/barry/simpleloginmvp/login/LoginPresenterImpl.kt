package com.barry.simpleloginmvp.login

class LoginPresenterImpl(val view: LoginContract.View) : LoginContract.Presenter {

    override fun validateCredentials(username: String, password: String) {
        if (username.isEmpty()) {
            view.showErrorUsername("Please input Username")

            return
        }

        if (password.isEmpty()) {
            view.showErrorPassword("Please input Password")

            return
        } else if (password.length < 8) {
            view.showErrorPassword("Please input valid Password")

            return
        }

        if (username == "barryjohn" && password == "qwerty123") {
            view.goToMain()
        } else {
            view.showErrorDialog("Incorrect Username or Password")
        }

    }
}