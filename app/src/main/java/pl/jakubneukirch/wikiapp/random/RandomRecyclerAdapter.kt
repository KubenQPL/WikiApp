package pl.jakubneukirch.wikiapp.random

import android.support.v7.widget.RecyclerView
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.random_item.view.*
import pl.jakubneukirch.wikiapp.R
import pl.jakubneukirch.wikiapp.data.model.dto.PageDTO

class RandomRecyclerAdapter : RecyclerView.Adapter<RandomRecyclerAdapter.ViewHolder>() {

    var list: List<PageDTO> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemClick: (View, Int) -> Unit = { _, _ -> }

    inner class ViewHolder(private val containerView: View) : RecyclerView.ViewHolder(containerView) {
        fun bind(title: String, description: Spanned, imageUrl: String, position: Int) {
            containerView.randomTitleTextView.text = title
            containerView.randomDescriptionTextView.text = description
            if (imageUrl.isNotEmpty()) {
                Picasso.get()
                        .load(imageUrl)
                        .into(containerView.randomImageView)
            }
            containerView.setOnClickListener {
                onItemClick(it, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.random_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position].title, list[position].description, list[position].imageUrl, position)
    }
}