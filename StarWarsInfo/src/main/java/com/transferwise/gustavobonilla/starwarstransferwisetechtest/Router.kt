/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * Router.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.detailinfo.view.DetailFragment
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.sectioninfo.view.SectionFragment

typealias CreateFragment = (String, String) -> Fragment

class Router(private val activity: FragmentActivity) {

  private val fragmentSelector: MutableMap<String, CreateFragment> = mutableMapOf(
      "Films" to {page, _ -> SectionFragment.newInstance(page)},
      "People" to {page, _ -> SectionFragment.newInstance(page)},
      "Planets" to {page, _ -> SectionFragment.newInstance(page)},
      "Species" to {page, _ -> SectionFragment.newInstance(page)},
      "Starships" to {page, _ -> SectionFragment.newInstance(page)},
      "Vehicles" to {page, _ -> SectionFragment.newInstance(page)},
      "Detail" to {id, url -> DetailFragment.newInstance(id, url)}
  )

  /**
   * Routes the a specific page based on the info in the [NavigationItem]
   *
   * @param navigationItem the [NavigationItem] that the navigation item that contains
   * the information to be routed to.
   */
  fun routesToPage(navigationItem: NavigationItem) {
    activity.run {
      val transaction = supportFragmentManager.beginTransaction()
      transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
      when (navigationItem.navigationType) {
        NavigationType.SECTION -> transaction.add(R.id.fragmentContainer, fragmentSelector[navigationItem.page]!!.invoke(navigationItem.page, "")).addToBackStack("Section")
        NavigationType.DETAIL -> transaction.add(R.id.fragmentContainer, fragmentSelector["Detail"]!!.invoke(navigationItem.page, navigationItem.url)).addToBackStack("Detail")
      }
      transaction.commit()
    }
  }
}