/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * Extensions.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Extension that allows a [ViewGroup] to inflate any layout.
 *
 * @param layoutRes the layout resource to be inflated.
 * @return the [View] inflated.
 */
fun ViewGroup.inflate(layoutRes: Int): View {
  return LayoutInflater.from(context).inflate(layoutRes, this, false)
}