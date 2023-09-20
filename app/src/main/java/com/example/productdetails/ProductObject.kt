package com.example.productdetails

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ProductObject {

    val baseUrl="https://dummyjson.com/"

    fun  getInstance(): ProductInterface {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ProductInterface::class.java)
    }}