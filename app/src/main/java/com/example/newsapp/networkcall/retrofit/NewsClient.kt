package com.example.newsapp.networkcall.retrofit

import com.example.newsapp.networkcall.model.Feed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsClient {


    @GET("/v2/everything")
    fun fetchNews(@Query("q")  q:String,
                  @Query("apiKey")  apiKey:String,
                  @Query("page")  page:Long,
                  @Query("pageSize")  pageSize:Int):Call<List<Feed>>
}