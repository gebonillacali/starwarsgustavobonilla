/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * RootAdapterItem.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.adapteritem

import android.support.v7.widget.RecyclerView
import android.view.View
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.R
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.AdapterItem
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.ClickListener
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.viewholder.RootViewHolder
import com.transferwise.gustavobonilla.swapi.model.RootItem

class RootAdapterItem(private val item: RootItem, private val clickListener: ClickListener): AdapterItem {
  override val layoutRes: Int
    get() = R.layout.root_view

  override fun bindData(viewHolder: RecyclerView.ViewHolder) {

    if (viewHolder !is RootViewHolder) {
      throw Exception("ViewHolder Should be a RootViewHolder instance")
    }
    viewHolder.title.text = item.title
    viewHolder.leftImageView.setImageResource(item.imageRes)
    viewHolder.rightImageView.setImageResource(item.imageRes)
    viewHolder.leftImageView.visibility = View.GONE
    viewHolder.rightImageView.visibility = View.GONE
    if (viewHolder.adapterPosition % 2 == 0) {
      viewHolder.leftImageView.visibility = View.VISIBLE
      viewHolder.itemView.setBackgroundResource(R.drawable.top_pane)
    } else {
      viewHolder.rightImageView.visibility = View.VISIBLE
      viewHolder.itemView.setBackgroundResource(R.drawable.top_pane_reverse)
    }
    viewHolder.itemView.isSelected = false
    viewHolder.itemView.setOnClickListener {
      it.isSelected = true
      clickListener.onItemClickListener(item.title, item.url)
    }
  }
}