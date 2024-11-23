package com.example.kotlin_project6.retrofit.api

import com.example.kotlin_project6.retrofit.model.Quotes
import retrofit2.http.GET
import retrofit2.http.Path

interface MainApi {
    @GET("quotes/{id}")
    suspend fun getQuoteById(@Path("id") id: Int): Quotes

}