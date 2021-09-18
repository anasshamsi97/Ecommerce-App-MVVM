package com.example.ecommerceapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<out VDB> : Fragment(), BaseView {

    private var progressBar: PopupWindow? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getViewId(), container, false)
        injectBinding(view)
        return view
    }

    override fun showSnack(resId: Int) {
        view?.let {
            Snackbar.make(it, getString(resId), Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun showProgressBar(view: View?) {
        activity?.let { (it as BaseActivity).showProgressBar(view) }
    }

    override fun hideProgressBar() {
        activity?.let { (it as BaseActivity).hideProgressBar() }
    }

    open fun handleArguments(arguments: Bundle) {}

    /** Abstract methods */
    abstract fun getViewId(): Int

    abstract fun injectBinding(view: View): VDB
}