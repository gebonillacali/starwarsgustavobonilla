/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * FilmAdapterItem.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.adapteritem

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.R
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.AdapterItem
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.ClickListener
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.viewholder.SectionItemViewHolder
import com.transferwise.gustavobonilla.swapi.model.Film

class FilmAdapterItem(val item: Film, val clickListener: ClickListener): AdapterItem {
  override val layoutRes: Int
    get() = R.layout.section_item_view

  override fun bindData(viewHolder: RecyclerView.ViewHolder) {
    if (viewHolder !is SectionItemViewHolder) {
      throw Exception("ViewHolder Should be a RootViewHolder instance")
    }

    viewHolder.sectionTitle.text = item.title
    viewHolder.firstTextItem.text = item.director
    viewHolder.secondTextItem.text = item.releaseDate

    viewHolder.leftImageView.visibility = View.GONE
    viewHolder.rightImageView.visibility = View.GONE

    if (viewHolder.adapterPosition % 2 == 0) {
      loadImageFilm(viewHolder.itemView, viewHolder.leftImageView, item.getUrlImageFilm())
      viewHolder.itemView.setBackgroundResource(R.drawable.top_pane)
    } else {
      loadImageFilm(viewHolder.itemView, viewHolder.rightImageView, item.getUrlImageFilm())
      viewHolder.itemView.setBackgroundResource(R.drawable.top_pane_reverse)
    }

    viewHolder.itemView.setOnClickListener {
      clickListener.onItemClickListener(item.id.toString(), item.url)
    }
  }

  /**
   * Loads the image for a film from Internet.
   *
   * @param view the root view that contains the [ImageView] which will hold the image.
   * @param imageView the [ImageView] that will hold the image.
   * @param urlImage the url for the image to be loaded in [ImageView]
   */
  private fun loadImageFilm(view: View, imageView: ImageView, urlImage: String) {
    val requestOptions = RequestOptions()
    requestOptions.placeholder(R.drawable.film)
    requestOptions.error(R.drawable.film)

    Glide.with(view)
        .setDefaultRequestOptions(requestOptions)
        .load(urlImage)
        .into(imageView)

    imageView.visibility = View.VISIBLE
  }
}