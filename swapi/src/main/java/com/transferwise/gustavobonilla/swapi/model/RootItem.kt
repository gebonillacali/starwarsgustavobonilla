/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * RootItem.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.swapi.model

data class RootItem(
    val title: String,
    val imageRes: Int,
    val url: String
    ): StarWarsModel() {
  override fun getItemUrl(): String {
    return ""
  }
}