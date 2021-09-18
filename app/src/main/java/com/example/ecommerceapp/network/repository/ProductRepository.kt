package com.example.ecommerceapp.network.repository

import com.example.ecommerceapp.base.BaseRepository
import com.example.ecommerceapp.network.ApiServices
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiServices: ApiServices
) : BaseRepository() {

    val products =  getResult { apiServices.getProducts() }

}