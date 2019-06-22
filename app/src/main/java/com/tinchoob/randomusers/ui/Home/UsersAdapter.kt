package com.tinchoob.randomusers.ui.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tinchoob.randomusers.R
import com.tinchoob.randomusers.data.model.Result
import com.tinchoob.randomusers.ui.UserDetail.ItemDetailActivity
import com.tinchoob.randomusers.ui.UserDetail.ItemDetailFragment
import kotlinx.android.synthetic.main.item_list_content.view.*


class UsersAdapter(
    private val parentActivity: ItemListActivity,
    private val values: List<Result>?,
    private val twoPane: Boolean
) :
    androidx.recyclerview.widget.RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Result
            if (twoPane) {
                val fragment = ItemDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(ItemDetailFragment.ARG_ITEM_ID, item.name?.first)
                    }
                }
                parentActivity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()
            } else {
                val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                    putExtra(ItemDetailFragment.ARG_ITEM_ID, item.name?.first)
                    putExtra(ItemDetailFragment.USER_IMAGE,item.picture?.large)
                }
                v.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values!![position]
        holder.idView.text = item.name?.first
        holder.contentView.text = item.name?.last

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values!!.size

    inner class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val idView: TextView = view.id_text
        val contentView: TextView = view.content
    }
}