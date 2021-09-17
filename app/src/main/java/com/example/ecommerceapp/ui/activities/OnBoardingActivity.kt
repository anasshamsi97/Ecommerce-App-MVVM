package com.example.ecommerceapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.example.ecommerceapp.R
import com.example.ecommerceapp.base.BaseActivity
import com.example.ecommerceapp.databinding.ActivityOnBoardingBinding
import com.example.ecommerceapp.ui.adapter.OnBoardingPagerAdapter


class OnBoardingActivity : BaseActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = OnBoardingPagerAdapter()
        val viewPager = binding.viewPager
        binding.nextButton.setOnClickListener {
            viewPager.currentItem = viewPager.currentItem + 1
        }
        binding.skipButton.setOnClickListener {
            startActivity(Intent(this@OnBoardingActivity, MainActivity::class.java))
            finish()
            overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
        }
        binding.finishButton.setOnClickListener {
            startActivity(Intent(this@OnBoardingActivity, MainActivity::class.java))
            finish()
            overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
        }
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                changeCircleBackground(position)
                if (position == 3) {
                    binding.nextButton.visibility = View.INVISIBLE
                    binding.skipButton.visibility = View.INVISIBLE
                    binding.finishButton.visibility = View.VISIBLE
                } else {
                    binding.finishButton.visibility = View.INVISIBLE
                    binding.nextButton.visibility = View.VISIBLE
                    binding.skipButton.visibility = View.VISIBLE
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
        viewPager.adapter = adapter

        changeCircleBackground(0)
    }

    private fun changeCircleBackground(i: Int) {
        val circlesLayout = binding.circlesLayout
        for (j in 0 until circlesLayout.childCount) {
            val circle = (circlesLayout.getChildAt(j) as View)
            circle.setBackgroundResource(
                if ((circle.tag.toString()
                        .toInt()) == i
                ) R.drawable.indicator_selected else R.drawable.indicator_unselected
            )
        }
    }
}