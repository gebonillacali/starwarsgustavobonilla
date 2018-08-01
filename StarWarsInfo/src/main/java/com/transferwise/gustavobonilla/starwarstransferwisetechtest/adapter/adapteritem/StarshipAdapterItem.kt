/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * StarshipAdapterItem.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.adapteritem

import android.support.v7.widget.RecyclerView
import android.view.View
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.R
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.AdapterItem
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.ClickListener
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.viewholder.SectionItemViewHolder
import com.transferwise.gustavobonilla.swapi.model.Starship

class StarshipAdapterItem(val item: Starship, val clickListener: ClickListener): AdapterItem {
  override val layoutRes: Int
    get() = R.layout.section_item_view

  override fun bindData(viewHolder: RecyclerView.ViewHolder) {
    if (viewHolder !is SectionItemViewHolder) {
      throw Exception("ViewHolder Should be a RootViewHolder instance")
    }

    viewHolder.sectionTitle.text = item.name
    viewHolder.firstTextItem.text = item.model
    viewHolder.secondTextItem.text = item.manufacturer
    viewHolder.leftImageView.setImageResource(R.drawable.starship)
    viewHolder.rightImageView.setImageResource(R.drawable.starship)
    viewHolder.leftImageView.visibility = View.GONE
    viewHolder.rightImageView.visibility = View.GONE

    if (viewHolder.adapterPosition % 2 == 0) {
      viewHolder.leftImageView.visibility = View.VISIBLE
      viewHolder.itemView.setBackgroundResource(R.drawable.top_pane)
    } else {
      viewHolder.rightImageView.visibility = View.VISIBLE
      viewHolder.itemView.setBackgroundResource(R.drawable.top_pane_reverse)
    }

    viewHolder.itemView.setOnClickListener {
      clickListener.onItemClickListener(item.id.toString(), item.url)
    }
  }
}