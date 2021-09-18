package com.example.ecommerceapp.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlin.properties.Delegates

abstract class BaseListAdapter<VDB, M> :
    RecyclerView.Adapter<BaseListAdapter<VDB, M>.ViewHolder>() {

    var items: List<M> by Delegates.observable(emptyList()) { _, old, new ->
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.executePendingBindings()
    }

    val itemClickSubject: PublishSubject<M> = PublishSubject.create<M>()
    override fun getItemCount() = items.size

    fun updateContent(items: List<M>?) {
        if (items.isNullOrEmpty())
            return
        this.items = items
    }

    internal fun ViewGroup.inflate(layoutRes: Int) =
        LayoutInflater.from(context).inflate(layoutRes, this, false)

    inner class ViewHolder(val binding: ViewDataBinding?) :
        RecyclerView.ViewHolder(binding!!.root)
}