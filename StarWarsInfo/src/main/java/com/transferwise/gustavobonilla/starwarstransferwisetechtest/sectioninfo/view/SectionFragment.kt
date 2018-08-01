/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * SectionFragment.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.sectioninfo.view

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
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.InfiniteScrollListener
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.ItemAdapter
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.sectioninfo.Section
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.sectioninfo.di.SectionModule
import com.transferwise.gustavobonilla.swapi.model.StarWarsModel
import kotlinx.android.synthetic.main.fragment_section.background
import kotlinx.android.synthetic.main.fragment_section.errorView
import kotlinx.android.synthetic.main.fragment_section.sectionRecyclerView
import javax.inject.Inject

class SectionFragment: Fragment(), Section.View, ClickListener {

  companion object {

    private const val KEY_PAGE = "PAGE"

    @JvmStatic
    fun newInstance(page: String) =
        SectionFragment().apply {
          arguments = Bundle().apply {
            putString(KEY_PAGE, page)
          }
        }
  }

  @Inject
  lateinit var presenter: Section.Presenter

  lateinit var adapter: ItemAdapter

  lateinit var navigation: Navigation

  //region Fragment Lifecycle
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val page = arguments?.getString(KEY_PAGE)
    page?.let {
      inject(it)
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_section, container, false)
  }

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    if (context is Navigation) {
      navigation = context
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRecyclerView()
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

  //region Section.View
  override fun setAdapterItems(itemsList: List<out StarWarsModel>) {
    adapter.addItems(itemsList)
  }

  override fun getAdapterItemCount(): Int {
    return adapter.itemCount
  }

  override fun showError() {
    sectionRecyclerView.visibility = View.GONE
    errorView.visibility = View.VISIBLE
  }

  override fun hideError() {
    sectionRecyclerView.visibility = View.VISIBLE
    errorView.visibility = View.GONE
  }
  //endregion

  //region Clicklistener
  override fun onItemClickListener(item: String, url: String) {
    navigation.navigateToPage(NavigationItem(item, url, NavigationType.DETAIL))
  }
  //endregion

  //region private methods
  /**
   * Setups the [android.support.v7.widget.RecyclerView] in the fragment.
   */
  private fun setupRecyclerView() {
    adapter = ItemAdapter(this)
    sectionRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    sectionRecyclerView.adapter = adapter
    val scrollListener = InfiniteScrollListener(4,
        onLoadingMore = { presenter.requestMoreItems() })
    sectionRecyclerView.addOnScrollListener(scrollListener)
  }

  /**
   * Injects the dependencies for the fragment.
   */
  private fun inject(page: String) {
    (activity?.application as StarWarsApplication)
        .starWarsApplicationComponent
        .plus(SectionModule(this, page))
        .inject(this)
  }
  //endregion
}
