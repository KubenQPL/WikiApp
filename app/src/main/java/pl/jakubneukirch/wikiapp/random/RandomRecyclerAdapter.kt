package pl.jakubneukirch.wikiapp.random

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.random_item.view.*
import pl.jakubneukirch.wikiapp.R
import pl.jakubneukirch.wikiapp.data.model.api.PageObject

class RandomRecyclerAdapter : RecyclerView.Adapter<RandomRecyclerAdapter.ViewHolder>() {
    var list: List<PageObject> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView) {
        fun bind(title: String, description: String) {
            containerView.pageTitleTextView.text = title
            containerView.pageDescriptionTextView.text = description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.random_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position].title, list[position].pageId.toString()) //todo after adding description to model swap it with pageId
    }
}