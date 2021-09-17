package com.example.ecommerceapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.ecommerceapp.R

class OnBoardingPagerAdapter : PagerAdapter() {
    override fun getCount(): Int {
        return 4
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater =
            container.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var resId = 0
        when (position) {
            0 -> resId = R.layout.onboard_1
            1 -> resId = R.layout.onboard_2
            2 -> resId = R.layout.onboard_3
            3 -> resId = R.layout.onboard_4
        }
        val view = layoutInflater.inflate(resId, null)
        container.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == (`object` as View)
    }
}