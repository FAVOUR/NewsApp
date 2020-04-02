package com.example.newsapp.networkcall.model

import com.google.gson.annotations.SerializedName

data class Feed (

    @SerializedName("status") val status : String,
    @SerializedName("totalResults") val totalResults : Int,
    @SerializedName("articles") val articles : List<Articles>
)