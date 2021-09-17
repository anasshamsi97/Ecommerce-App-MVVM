package com.example.ecommerceapp.ui.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.core.content.ContextCompat
import com.example.ecommerceapp.R
import com.example.ecommerceapp.base.BaseActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val logoImage = findViewById<View>(R.id.logo_layout)

        Handler(Looper.getMainLooper()).postDelayed({
            val widthAnimator =
                ValueAnimator.ofInt(logoImage.width, (logoImage.parent as View).measuredWidth)
            widthAnimator.duration = 500
            widthAnimator.interpolator = DecelerateInterpolator()
            widthAnimator.addUpdateListener { animation ->
                logoImage.layoutParams.width = animation.animatedValue as Int
                logoImage.requestLayout()
            }
            widthAnimator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)

                    Handler(Looper.getMainLooper()).postDelayed({
                        startActivity(Intent(this@SplashActivity,OnBoardingActivity::class.java))
                        finish()
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
                    }, 250)
                }
            })
            widthAnimator.start()
        }, 500)
    }


}