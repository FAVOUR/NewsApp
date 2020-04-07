package com.example.newsapp.pagination

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.newsapp.helper.State
import com.example.newsapp.helper.setLog
import com.example.newsapp.helper.setTag
import com.example.newsapp.networkcall.model.Feed
import com.example.newsapp.networkcall.retrofit.RestService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class NewDataSource(context: Context):PageKeyedDataSource<Long,Feed>() {

    var loading=MutableLiveData<String>()
    var finishLoading=MutableLiveData<String>()

    init {
        setTag(context)
    }




    val newsApi=RestService.create()

    val state:MutableLiveData<State>
        get() = MutableLiveData()


    private fun updateState(state: State) {
        this.state.postValue(state)
    }


     var API_KEY:String="079dac74a5f94ebdb990ecf61c8854b7"
     var Q:String="movies"
     var PAGE:Long=1
     var PAGE_SIZE:Int=20
    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, Feed>) {
        updateState(State.LOADING)
        setLog("About to load ")
        setLog("About to load  ${params.requestedLoadSize}")
        newsApi.fetchNews(apiKey =API_KEY ,q=Q,page =PAGE ,pageSize = PAGE_SIZE).enqueue(object :Callback<List <Feed>>{
            override fun onFailure(call: Call<List <Feed>>, t: Throwable) {

                updateState(State.ERROR)
                setLog("Failed to load ")
            }

            override fun onResponse(call: Call<List <Feed>>, response: Response<List <Feed>>) {
                updateState(State.DONE)
                if(response.isSuccessful) {
                    setLog("Successful!")
                    callback.onResult(response.body()!!,PAGE,PAGE+1L)
                }else{

                    setLog("Failed")

                }

            }

        })


    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Feed>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Feed>) {
        updateState(State.LOADING)
        setLog("About to load ")
        setLog("About to load params.requestedLoadSize   ${params.requestedLoadSize}")
        setLog("About to load params.key  ${params.key}")
        newsApi.fetchNews(apiKey =API_KEY ,q=Q,page =params.key ,pageSize = params.requestedLoadSize).enqueue(object :Callback<List <Feed>>{
            override fun onFailure(call: Call<List <Feed>>, t: Throwable) {

                updateState(State.ERROR)
                setLog("Failed to load ")
            }

            override fun onResponse(call: Call<List <Feed>>, response: Response<List <Feed>>) {
                updateState(State.DONE)
                if(response.isSuccessful) {
                    setLog("Successful!")
                    val nextKey =
                        (if (params.key === response.body()?.size?.toLong()) null else params.key + 1)?.toLong()

                    callback.onResult(response.body()!!,nextKey)
                }else{

                    setLog("Failed")

                }

            }

        })    }
}