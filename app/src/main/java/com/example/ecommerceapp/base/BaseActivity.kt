package com.example.ecommerceapp.base

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import com.example.ecommerceapp.R

open class BaseActivity: AppCompatActivity(), BaseView {

    private var progressBar: PopupWindow? = null

    override fun showSnack(resId: Int) {

    }

    override fun showProgressBar(view: View?) {
        if (progressBar != null) return

        val inflater =
            getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        val progressBarView =
            inflater!!.inflate(R.layout.view_progressbar, null)
        progressBar =
            PopupWindow(
                progressBarView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        Handler(Looper.getMainLooper()).post {
            progressBar?.showAtLocation(view, Gravity.CENTER, 0, 0)
        }
    }

    override fun hideProgressBar() {
        if (progressBar == null) return

        progressBar?.dismiss()
        progressBar = null
    }
}