/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * RootViewHolder.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.R

class RootViewHolder(view: View): RecyclerView.ViewHolder(view) {
  val title: TextView = view.findViewById(R.id.rootTitle)
  val leftImageView: ImageView = view.findViewById(R.id.leftImage)
  val rightImageView: ImageView = view.findViewById(R.id.rightImage)
}