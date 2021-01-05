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

class FollowerAdapter: RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
    private val mData = ArrayList<FollowerItem>()
    fun setData(items : ArrayList<FollowerItem>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = mData.size

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        position: Int
    ): FollowerAdapter.FollowerViewHolder
    {
        val layoutInflater = LayoutInflater.from(viewGroup.context).inflate((R.layout.recyclerview_follower), viewGroup,false)
        return FollowerViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(followerViewHolder: FollowerViewHolder, position: Int) {
        followerViewHolder.bind(mData[position])

    }

    inner class FollowerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(follower: FollowerItem) {
            with(itemView){
                Name.text = follower.login
                linkgithub.text = follower.url
                Glide.with(this).load(follower.avatar_url).into(Image)
            }
        }
    }
}