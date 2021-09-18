package com.example.ecommerceapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerceapp.R
import com.example.ecommerceapp.base.BaseFragment
import com.example.ecommerceapp.databinding.FragmentHomeBinding
import com.example.ecommerceapp.databinding.RowProductBinding
import com.example.ecommerceapp.model.product.Product
import com.example.ecommerceapp.network.Result
import com.example.ecommerceapp.ui.adapter.SimpleListAdapter
import com.example.ecommerceapp.ui.utils.GridSpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = injectBinding(view)
        super.onViewCreated(view, savedInstanceState)


        getProducts()
    }

    private fun getProducts() {
        val productRecyclerView = binding.productRecyclerView
        productRecyclerView.layoutManager =  GridLayoutManager(context,2)
        productRecyclerView.addItemDecoration(
            GridSpacingItemDecoration(
                2,
                25,
                true
            )
        )
        val productAdapter =
            SimpleListAdapter<RowProductBinding, Product>(R.layout.row_product)


        viewModel.products.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> showProgressBar(view)
                is Result.Success -> {
                    hideProgressBar()
                    result.data.let { response ->
                        if (response.isNotEmpty())
                            productAdapter.updateContent(response)
                        productRecyclerView.adapter = productAdapter
                    }
                }
                else -> {
                    hideProgressBar()
                }
            }
        })
    }

    override fun getViewId() = R.layout.fragment_home

    override fun injectBinding(view: View) = DataBindingUtil.bind<FragmentHomeBinding>(view)!!

}