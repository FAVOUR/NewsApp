package com.example.newsapp.pagination

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.newsapp.networkcall.model.Articles
import com.example.newsapp.networkcall.model.Feed

/**
 * Created by Olije Favour on 4/8/2020.
 */
class NewsDataSourceFactory(var context: Context) :DataSource.Factory<Long , Articles>(){

    val mNewsDataSource:MutableLiveData<NewsDataSource>
              get() = MutableLiveData()



    override fun create(): DataSource<Long, Articles> {
         val newsDataSource= NewsDataSource(context)

          mNewsDataSource.postValue(newsDataSource)

        return newsDataSource

    }


}