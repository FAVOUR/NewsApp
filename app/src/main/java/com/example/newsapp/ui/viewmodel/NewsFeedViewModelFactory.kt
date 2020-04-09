package com.example.newsapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Olije Favour on 4/9/2020.
 */



    class NewsFedViewModelFactory(var context: Context): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(NewsFeedVm::class.java)){
                    return NewsFeedVm(context) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")

        }

    }
