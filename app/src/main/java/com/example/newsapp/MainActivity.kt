package com.example.newsapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.helper.State
import com.example.newsapp.helper.setLog
import com.example.newsapp.helper.setTag
import com.example.newsapp.ui.recyclerview.adapter.NewsFeedAdapter
import com.example.newsapp.ui.viewmodel.NewsFeedViewModelFactory
import com.example.newsapp.ui.viewmodel.NewsFeedVm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
      var viewModel:NewsFeedVm?=null
    lateinit  var newsListAdapter:NewsFeedAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel= ViewModelProviders.of(this, NewsFeedViewModelFactory(this))[NewsFeedVm::class.java]

       setTag(this)
        initState()

        initAdapter(this)
    }


    private fun initState() {
        txt_error.setOnClickListener {
            viewModel=null
            viewModel= ViewModelProviders.of(this, NewsFeedViewModelFactory(this))[NewsFeedVm::class.java]

            viewModel!!.retry() }
        viewModel!!.getState().observe(this, Observer { state ->

            setLog("initState State.LOADING   ${State.LOADING}")
            setLog("initState state == State.ERROR   ${state == State.ERROR}")

            progress_bar.visibility = if (viewModel!!.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if (viewModel!!.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE
            if (!viewModel!!.listIsEmpty()) {
                newsListAdapter.setState(state ?: State.DONE)
            }
        })
    }


    private fun initAdapter(context: Context) {
//        newsListAdapter = NewsListAdapter { viewModel.retry() }
        newsListAdapter = NewsFeedAdapter(context = context )

        setLog("initAdapter here ")

        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler_view.adapter = newsListAdapter
        viewModel!!.newsFeed .observe(this, Observer {
            newsListAdapter.submitList(it)
        })
    }

}
