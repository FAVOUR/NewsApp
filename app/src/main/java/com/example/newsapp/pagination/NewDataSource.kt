package com.example.newsapp.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.newsapp.networkcall.model.Feed


class NewDataSource:PageKeyedDataSource<Long,Feed>() {

    var loading=MutableLiveData<String>()
    var finishLoading=MutableLiveData<String>()

    private val TAG: String = NewDataSource::class.java.getSimpleName()


    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Feed>) {


    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Feed>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Feed>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}