/*
 * Copyright (c) 2018 Gustavo E Bonilla - TransferWise Tech Test
 *
 * StarWarsTransferWiseTechTest
 * StarWarsModel.kt
 *
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: julio 27, 2018
 */

package com.transferwise.gustavobonilla.swapi.model

abstract class StarWarsModel {

  /**
   * Gets the url for any StarWars Model.
   *
   * @return the url in string format.
   */
  protected abstract fun getItemUrl(): String

  val id: Int get() = getItemUrl().split("/").last().toInt()
}