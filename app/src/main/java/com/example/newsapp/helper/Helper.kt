package com.example.newsapp.helper

import android.app.Activity
import android.content.Context
import android.util.Log
import com.example.newsapp.pagination.NewDataSource
import kotlin.reflect.KClass

/**
 * Created by Olije Favour on 4/7/2020.
 */


val setTag:(context: Context)->Unit={ context ->
    App.TAG = (context as Activity).localClassName
}



val setLog:(message:String)->Unit={message ->
    Log.i(App.TAG,message)
}
enum class State (messege:String=""){
    DONE,
    LOADING,
    ERROR,
    FAILED

}