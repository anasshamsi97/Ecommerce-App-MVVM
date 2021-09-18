package com.example.ecommerceapp.network

import com.example.ecommerceapp.network.RemoteDataSource.Companion.PRODUCTS_URL
import com.example.ecommerceapp.model.response.GetProductsResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {


    @GET(PRODUCTS_URL)
    suspend fun getProducts(): Response<GetProductsResponse>

}