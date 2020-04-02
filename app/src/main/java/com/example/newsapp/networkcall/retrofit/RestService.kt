package com.example.newsapp.networkcall.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestService {

     val BASEURL:String =""

    fun create ():NewsClient{
        val httpLogger =HttpLoggingInterceptor()
        httpLogger.level=HttpLoggingInterceptor.Level.BODY

       var client :OkHttpClient.Builder= OkHttpClient.Builder()
        client.addInterceptor(httpLogger)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()

        return  retrofit.create(NewsClient::class.java)



    }
}