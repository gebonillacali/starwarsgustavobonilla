/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * MainActivity.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.home.view.HomeFragment

class MainActivity: AppCompatActivity(), Navigation {

  private val router = Router(this)

  //region Activity Lifecycle
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    initApplication()
  }
  //endregion

  //region Navigation
  override fun navigateToPage(navigationItem: NavigationItem) {
    router.routesToPage(navigationItem)
  }
  //endregion

  //region private
  /**
   * Initialize the application.
   */
  private fun initApplication() {
    val homeFragment = HomeFragment.newInstance()
    addFragment(homeFragment)
  }

  /**
   * Add a fragment into the [Activity].
   *
   * @param fragment the fragment to be added into the [Activity]
   */
  private fun addFragment(fragment: Fragment) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.add(R.id.fragmentContainer, fragment)
    transaction.commit()
  }
  //endregion
}
