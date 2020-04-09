package com.example.newsapp.ui.recyclerview.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.networkcall.model.Articles
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*

/**
 * Created by Olije Favour on 4/9/2020.
 */
class ArticlesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(news: Articles?) {
        if (news != null) {

            itemView.txt_news_name.text = news.title
            Picasso.get().load(news.image).into(itemView.img_news_banner)
        }
    }

    companion object {
        fun create(parent: ViewGroup): ArticlesViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news, parent, false)
            return ArticlesViewHolder(view)
        }
    }
}