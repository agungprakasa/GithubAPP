package com.example.dicodingsub2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dicodingsub2.R
import com.example.dicodingsub2.models.Item
import kotlinx.android.synthetic.main.item_preview.view.*

class GithubAdapter: RecyclerView.Adapter<GithubAdapter.GithubViewHolder>() {
    inner class GithubViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder {
        return  GithubViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_preview,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.avatar_url).into(ivArticleImage)
            tvTitle.text = article.login
            tvDescription.text = article.html_url
            setOnClickListener {
                onItemClickListener?.let { it(article)}
            }
        }
    }

    private var onItemClickListener: ((Item)-> Unit)? = null

    fun setOnClickListener(listener: (Item)-> Unit){
        onItemClickListener = listener
    }
}