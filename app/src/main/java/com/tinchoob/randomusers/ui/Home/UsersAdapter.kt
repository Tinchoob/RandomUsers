package com.tinchoob.randomusers.ui.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.tinchoob.randomusers.R
import com.tinchoob.randomusers.data.model.Result
import kotlinx.android.synthetic.main.item_list_content.view.*


class UsersAdapter(
    private val parentActivity: ItemListActivity,
    private var values: MutableList<Result>?,
    private val twoPane: Boolean,
    private val userSelectedListener : OnUserSelectedListener
) :
    androidx.recyclerview.widget.RecyclerView.Adapter<UsersAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)

        view.setOnClickListener {
                userSelectedListener.onUserSelected(view.tag as Result)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values!![position]
        holder.itemDescription.text = String.format("%s %s",item.name?.last,item.name?.first)

        Glide.with(parentActivity)
            .load(item.picture?.thumbnail)
            .into(holder.userThumbnail)

        with(holder.itemView) {
            tag = item
        }

    }

    fun addMoreUsers(newValues: MutableList<Result>){
    values?.addAll(newValues)
        notifyDataSetChanged()
    }

    override fun getItemCount() = values!!.size

    inner class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val itemDescription: TextView = view.item_description
        val userThumbnail : ImageView = view.user_thumbnail
    }
}