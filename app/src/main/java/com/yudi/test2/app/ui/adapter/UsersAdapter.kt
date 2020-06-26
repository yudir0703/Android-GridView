package com.yudi.test2.app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yudi.test2.R
import com.yudi.test2.api.models.users.UserItem
import com.yudi.test2.app.common.setImage
import kotlinx.android.synthetic.main.main_fragment.view.*


/**
 * @author Yudi Rahmat
 */

class UsersAdapter(listData: MutableList<UserItem?>?): CoreAdapter<UserItem>() {
    private val layoutStory = R.layout.view_row_user

    init {
        this.list = listData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context

        return ViewHolder(LayoutInflater.from(parent.context).inflate(layoutStory, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind()
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val item: UserItem? = list?.get(adapterPosition)

            setImage(itemView.iv_preview, item?.avatarUrl.toString(), context!!)
        }
    }
}
