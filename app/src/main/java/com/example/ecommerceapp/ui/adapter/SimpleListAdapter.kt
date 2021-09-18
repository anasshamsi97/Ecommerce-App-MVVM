package com.example.ecommerceapp.ui.adapter

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.ecommerceapp.BR
import com.example.ecommerceapp.base.BaseListAdapter

open class SimpleListAdapter<VDB, M>(@LayoutRes private val resId: Int) :
    BaseListAdapter<VDB, M>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewDataBinding = DataBindingUtil.bind<ViewDataBinding>(parent.inflate(resId))
        val holder = ViewHolder(viewDataBinding)
        viewDataBinding?.root?.setOnClickListener {
            val model = items[holder.adapterPosition]
            itemClickSubject.onNext(model)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        binding?.setVariable(BR.model, items[position])
        super.onBindViewHolder(holder, position)
    }
}