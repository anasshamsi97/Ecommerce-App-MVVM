package com.example.ecommerceapp.base

import android.view.View

interface BaseView {
    fun showSnack(resId: Int)
    fun showProgressBar(view: View?)
    fun hideProgressBar()
}