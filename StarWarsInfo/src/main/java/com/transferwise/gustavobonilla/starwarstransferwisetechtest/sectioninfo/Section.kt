/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * Section.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.sectioninfo

import com.transferwise.gustavobonilla.starwarstransferwisetechtest.Lifecycle
import com.transferwise.gustavobonilla.swapi.model.StarWarsModel

interface Section {
  interface View {
    /**
     * Sets the adapter items.
     */
    fun setAdapterItems(itemsList: List<out StarWarsModel>)

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

    /**
     * Request more items when scrolling the section items.
     */
    fun requestMoreItems()
  }
}