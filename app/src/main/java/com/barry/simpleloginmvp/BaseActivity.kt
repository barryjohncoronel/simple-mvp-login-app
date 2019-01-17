package com.barry.simpleloginmvp

import android.support.v7.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity() {

    protected val TAG = javaClass.simpleName

    abstract fun setupViews()
}