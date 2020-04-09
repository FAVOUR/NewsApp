package com.example.newsapp.ui.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.newsapp.helper.App
import com.example.newsapp.helper.State
import com.example.newsapp.helper.setLog
import com.example.newsapp.networkcall.model.Articles
import com.example.newsapp.networkcall.model.Feed
import com.example.newsapp.pagination.NewsDataSource
import com.example.newsapp.pagination.NewsDataSourceFactory

/**
 * Created by Olije Favour on 4/8/2020.
 */
class NewsFeedVm(var _context:Context) :ViewModel(){

    lateinit var dataSourceFactory :NewsDataSourceFactory
  lateinit  var newsFeed :LiveData<PagedList<Articles>>



    init {


        makeNetworkcall()




    }

    fun makeNetworkcall(){
        setLog("here we go  retry")


        val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(10)
        .setPageSize(20)
        .build()

        setLog("Init  retry")

        dataSourceFactory=NewsDataSourceFactory(_context)

        newsFeed= LivePagedListBuilder<Long,Articles>(dataSourceFactory,config).build()
    }



    fun retry(){
        setLog("About to retry")
        makeNetworkcall()
    }



    fun listIsEmpty(): Boolean {
        return newsFeed.value?.isEmpty() ?: true
    }
//       Transformations.switchMap(dataSource.mNewsDataSource , {dataSource -> dataSource.state})

    fun getState(): LiveData<State> = Transformations.switchMap(dataSourceFactory.mNewsDataSource , NewsDataSource::state)


}