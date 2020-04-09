package com.example.newsapp.ui.recyclerview.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.helper.State
import com.example.newsapp.helper.setLog
import com.example.newsapp.helper.setTag
import com.example.newsapp.networkcall.model.Articles
import com.example.newsapp.ui.recyclerview.viewholder.ArticlesViewHolder
import com.example.newsapp.ui.recyclerview.viewholder.ListFooterViewHolder

/**
 * Created by Olije Favour on 4/9/2020.
 */
class NewsFeedAdapter(var context:Context):PagedListAdapter<Articles,RecyclerView.ViewHolder>(diffUtil) {

    private val DATA_VIEW_TYPE = 1
    private val FOOTER_VIEW_TYPE = 2

    private var state = State.LOADING



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        setTag(context)

        setLog("onCreateViewHolder  viewType $viewType")
//        setLog("onBindViewHolder  getItemViewType(position) ${getItemViewType(position)}")
      return  if(viewType ==DATA_VIEW_TYPE)ArticlesViewHolder.create(parent)else{ListFooterViewHolder.create(parent)}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
   setLog("onBindViewHolder  Position $position")
   setLog("onBindViewHolder  getItemViewType(position) ${getItemViewType(position)}")

        if (getItemViewType(position) == DATA_VIEW_TYPE)
            (holder as ArticlesViewHolder).bind(getItem(position))
        else (holder as ListFooterViewHolder).bind(state)    }

    override fun getItemViewType(position: Int): Int {

        setLog("getItemViewType    Position $position")
        setLog("getItemViewType    getItemViewType(position) ${getItemViewType(position)}")
        return if (position < super.getItemCount()) DATA_VIEW_TYPE else FOOTER_VIEW_TYPE
    }

/*    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsFeedViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: NewsFeedViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }*/


    companion object{
          var diffUtil=object :DiffUtil.ItemCallback<Articles>(){
            override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
                return  newItem.title==oldItem.title
            }

            override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
                return  newItem==oldItem


            }

        }
    }


    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == State.LOADING || state == State.ERROR)
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }
}




/*
class NewsFeedViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){


}*/
