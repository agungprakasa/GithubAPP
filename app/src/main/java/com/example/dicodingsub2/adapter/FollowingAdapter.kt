package com.example.dicodingsub2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dicodingsub2.R
import com.example.dicodingsub2.models.FollowerItem
import com.example.dicodingsub2.models.followingItem
import kotlinx.android.synthetic.main.recyclerview_follower.view.*

class FollowingAdapter : RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>(){
    private val mData = ArrayList<followingItem>()
    fun setData(items : ArrayList<followingItem>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = mData.size

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        position: Int
    ): FollowingAdapter.FollowingViewHolder
    {
        val layoutInflater = LayoutInflater.from(viewGroup.context).inflate((R.layout.recyclerview_follower), viewGroup,false)
        return FollowingViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(followingViewHolder: FollowingViewHolder, position: Int) {
        followingViewHolder.bind(mData[position])

    }

    inner class FollowingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(following: followingItem) {
            with(itemView){
                Name.text = following.login
                linkgithub.text = following.url
                Glide.with(this).load(following.avatar_url).into(Image)
            }
        }
    }
}