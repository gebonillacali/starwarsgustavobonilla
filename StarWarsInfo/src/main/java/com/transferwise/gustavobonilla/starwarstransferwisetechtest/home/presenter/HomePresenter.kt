/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * HomePresenter.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.home.presenter

import android.arch.persistence.room.EmptyResultSetException
import android.util.Log
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.R
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.home.Home
import com.transferwise.gustavobonilla.swapi.model.StarWarsModel
import com.transferwise.gustavobonilla.swapi.repository.ItemRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(
    private val view: Home.View,
    private val repository: ItemRepository,
    private val subscribeOn: Scheduler = Schedulers.io(),
    private val observeOn: Scheduler = AndroidSchedulers.mainThread()): Home.Presenter {

  companion object {
    //Due to the lack of Images in swapi.co (Starwars API), I create a set of stencil images.
    private val imagesList = listOf(R.drawable.film, R.drawable.people, R.drawable.planet, R.drawable.species, R.drawable.starship, R.drawable.vehicles)
  }

  private var disposables = CompositeDisposable()

  //region Home.Presenter
  override fun init() {
    requestRoot()
  }
  //endregion

  //region Lifecycle.Callback
  override fun onDestroy() {
    disposables.dispose()
  }
  //endregion

  //region private
  /**
   * Request and load the info for the home screen.
   */
  private fun requestRoot() {
    view.showError()
    val disposable = repository.getRoot()
        .subscribeOn(subscribeOn)
        .observeOn(observeOn)
        .subscribe({
          Log.w("HomePresenter", "Getting values")
          val listElements = it.getDataAsList(imagesList)
          if (listElements.isNotEmpty() && view.getAdapterItemCount() <= 0) {
            view.setAdapterItems(listElements as List<StarWarsModel>)
            view.hideError()
          }
        }, {
        })
    disposables.add(disposable)
  }
  //endregion
}