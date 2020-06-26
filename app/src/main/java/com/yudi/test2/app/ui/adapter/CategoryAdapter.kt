package com.yudi.test2.app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.yudi.test2.R
import com.yudi.test2.api.models.category.CategoryItem
import com.yudi.test2.api.interfaces.OnItemClickListener
import kotlinx.android.synthetic.main.view_row_category.view.*


/**
 * @author Yudi Rahmat
 */

class CategoryAdapter(listData: MutableList<CategoryItem?>?, listener: OnItemClickListener): CoreAdapter<CategoryItem>() {
    private val layoutStory     = R.layout.view_row_category
    var selectedPos     = -1

    init {
        this.list       = listData
        this.listener   = listener
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
            val item: CategoryItem? = list?.get(adapterPosition)

            itemView.tv_name.text = item?.name
            itemView.tv_name.setBackgroundColor(if(selectedPos == adapterPosition) ContextCompat.getColor(context!!, R.color.transparent) else ContextCompat.getColor(context!!, R.color.colorGreySoft))
            itemView.setOnClickListener {
                listener?.onItemClicked(adapterPosition)
                selectedPos = adapterPosition
                notifyDataSetChanged()
            }
        }
    }
}
