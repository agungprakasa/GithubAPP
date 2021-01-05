package com.example.consumeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.consumeapp.models.Item
import kotlinx.android.synthetic.main.detail_item.view.*

class ConsumeAdapter: RecyclerView.Adapter<ConsumeAdapter.ConsumeViewHolder>() {
    private val mData = ArrayList<Item>()
    fun setData(items : ArrayList<Item>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = mData.size

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        position: Int
    ): ConsumeAdapter.ConsumeViewHolder
    {
        val layoutInflater = LayoutInflater.from(viewGroup.context).inflate((R.layout.detail_item), viewGroup,false)
        return ConsumeViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(consumeViewHolder: ConsumeViewHolder, position: Int) {
        consumeViewHolder.bind(mData[position])

    }

    inner class ConsumeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(follower: Item) {
            with(itemView){
                tvTitle.text = follower.login
                tvDescription.text = follower.url
                Glide.with(this).load(follower.avatar_url).into(ivArticleImage)
            }
        }
    }
}