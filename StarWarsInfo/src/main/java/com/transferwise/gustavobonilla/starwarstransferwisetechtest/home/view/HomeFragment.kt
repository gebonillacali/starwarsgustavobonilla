/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * HomeFragment.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.home.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.Navigation
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.NavigationItem
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.NavigationType
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.R
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.StarWarsApplication
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.ClickListener
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.ItemAdapter
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.home.Home
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.home.di.HomeModule
import com.transferwise.gustavobonilla.swapi.model.StarWarsModel
import kotlinx.android.synthetic.main.fragment_home.background
import kotlinx.android.synthetic.main.fragment_home.errorView
import kotlinx.android.synthetic.main.fragment_home.homeRecyclerView
import javax.inject.Inject

class HomeFragment: Fragment(), Home.View, ClickListener {

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

  lateinit var adapter: ItemAdapter

  lateinit var navigation: Navigation

  //region Fragment Lifecycle
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    inject()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_home, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRecyclerView()
  }

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    if (context is Navigation) {
      navigation = context
    }
  }

  override fun onResume() {
    super.onResume()
    background.registerSensorManager()
    presenter.init()
  }

  override fun onPause() {
    background.unregisterSensorManager()
    super.onPause()
  }

  override fun onDestroy() {
    presenter.onDestroy()
    super.onDestroy()
  }
  //endregion

  //region Home.View
  override fun setAdapterItems(itemsList: List<out StarWarsModel>) {
    adapter.addItems(itemsList)
  }

  override fun getAdapterItemCount(): Int {
    return adapter.itemCount
  }

  override fun showError() {
    homeRecyclerView.visibility = View.GONE
    errorView.visibility = View.VISIBLE
  }

  override fun hideError() {
    homeRecyclerView.visibility = View.VISIBLE
    errorView.visibility = View.GONE
  }
  //endregion

  //region Clicklistener
  override fun onItemClickListener(item: String, url: String) {
    navigation.navigateToPage(NavigationItem(item, url, NavigationType.SECTION))
  }
  //endregion

  //region private
  /**
   * Setups the [android.support.v7.widget.RecyclerView] in the fragment.
   */
  private fun setupRecyclerView() {
    adapter = ItemAdapter(this)

    homeRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    homeRecyclerView.adapter = adapter
  }

  /**
   * Injects the dependencies in the fragment.
   */
  private fun inject() {
    (activity?.application as StarWarsApplication)
        .starWarsApplicationComponent
        .plus(HomeModule(this))
        .inject(this)
  }
  //endregion
}
