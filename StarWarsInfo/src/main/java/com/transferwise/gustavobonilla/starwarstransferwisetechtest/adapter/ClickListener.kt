/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * ClickListener.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter

interface ClickListener {

  /**
   * Defines the click listener for items.
   *
   * @param item the info for the item
   * @param url the url for the item.
   */
  fun onItemClickListener(item: String, url: String)
}