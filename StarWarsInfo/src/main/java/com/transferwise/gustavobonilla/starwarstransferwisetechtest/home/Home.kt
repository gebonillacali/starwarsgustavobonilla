/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * Home.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.home

import com.transferwise.gustavobonilla.starwarstransferwisetechtest.Lifecycle
import com.transferwise.gustavobonilla.swapi.model.StarWarsModel

interface Home {

  interface View {
    /**
     * Sets the adapter items.
     */
    fun setAdapterItems(itemsList: List<StarWarsModel>)

    /**
     * Gets the number of elements in adapter.
     */
    fun getAdapterItemCount(): Int

    /**
     * Shows error message when necessary.
     */
    fun showError()

    /**
     * Hide error message when necessary.
     */
    fun hideError()
  }

  interface Presenter: Lifecycle.Callback {
    /**
     * Initialize the presenter.
     */
    fun init()
  }
}