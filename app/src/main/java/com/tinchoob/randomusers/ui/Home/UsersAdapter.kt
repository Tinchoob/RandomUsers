package com.tinchoob.randomusers.ui.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.tinchoob.randomusers.R
import com.tinchoob.randomusers.data.model.Result
import com.tinchoob.randomusers.ui.UserDetail.ItemDetailActivity
import com.tinchoob.randomusers.ui.UserDetail.ItemDetailFragment
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_list_content.view.*


class UsersAdapter(
    private val parentActivity: ItemListActivity,
    private val values: List<Result>?,
    private val twoPane: Boolean,
    private val userSelectedListener : OnUserSelectedListener
) :
    androidx.recyclerview.widget.RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

//    private val onClickListener: View.OnClickListener
//
//    init {
//        onClickListener = View.OnClickListener { v ->
//
//        }
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)

        view.setOnClickListener {
                userSelectedListener.OnUserSelected(view.tag as Result)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values!![position]
        holder.idView.text = position.toString()
        holder.contentView.text = item.name?.last

        Glide.with(parentActivity)
            .load(item.picture?.thumbnail)
            .into(holder.userThumbnail)

        with(holder.itemView) {
            tag = item
        }

    }

    override fun getItemCount() = values!!.size

    inner class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val idView: TextView = view.id_text
        val contentView: TextView = view.content
        val userThumbnail : ImageView = view.user_thumbnail
    }
}