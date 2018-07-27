/*
 * Copyright (c) 2018 Gustavo E Bonilla - TransferWise Tech Test
 *
 * StarWarsTransferWiseTechTest
 * HomeFragment.kt
 *
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 26, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.home.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.R
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.StarWarsApplication
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.home.Home
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.home.di.HomeModule
import javax.inject.Inject

class HomeFragment: Fragment(), Home.View {

  companion object {
    @JvmStatic
    fun newInstance(): HomeFragment {
      return HomeFragment().apply {
        arguments = Bundle().apply {
        }
      }
    }
  }

  @Inject
  lateinit var presenter: Home.Presenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    inject()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_home, container, false)
  }

  override fun onResume() {
    super.onResume()
    presenter.init()
  }

  //region Home.View
  override fun showInfo(message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
  }
  //endregion

  private fun inject() {
    (activity?.application as StarWarsApplication)
        .starWarsApplicationComponent
        .plus(HomeModule(this))
        .inject(this)
  }
}
