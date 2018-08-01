/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * AdapterItem.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Defines the function for creating a [RecyclerView.ViewHolder]
 */
typealias CreateViewHolder = (Int, ViewGroup) -> RecyclerView.ViewHolder

interface AdapterItem {

  val layoutRes: Int

  /**
   * Binds the data into the view holder.
   *
   * @param viewHolder the [RecyclerView.ViewHolder] which contains the views to be populated
   */
  fun bindData(viewHolder: RecyclerView.ViewHolder)
}