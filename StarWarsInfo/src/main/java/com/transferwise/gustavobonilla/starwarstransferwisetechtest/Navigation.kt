/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * Navigation.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest

/**
 * Defines a model for [Navigation].
 */
data class NavigationItem(val page: String, val url: String, val navigationType: NavigationType)

/**
 * Defines the types of Navigation in application.
 */
enum class NavigationType {
  SECTION,
  DETAIL
}

interface Navigation {

  /**
   * Navigates to a specific page.
   *
   * @param navigationItem the navigation item that contains the information to navigate to.
   */
  fun navigateToPage(navigationItem: NavigationItem)
}