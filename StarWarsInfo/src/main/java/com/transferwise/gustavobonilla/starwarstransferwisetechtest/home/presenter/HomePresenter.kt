/*
 * Copyright (c) 2018 Gustavo E Bonilla - TransferWise Tech Test
 *
 * StarWarsTransferWiseTechTest
 * HomePresenter.kt
 *
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 26, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.home.presenter

import android.util.Log
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.home.Home
import com.transferwise.gustavobonilla.swapi.api.StarWarsApi
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePresenter(
    private val view: Home.View,
    private val starWarsApi: StarWarsApi,
    private val subscribeOn: Scheduler = Schedulers.io(),
    private val observeOn: Scheduler = AndroidSchedulers.mainThread()): Home.Presenter {

  companion object {
    private const val INITIAL_PAGE = 1
  }

  override fun init() {
    requestRoot()
  }

  private fun requestRoot() {
    starWarsApi.api().getAllFilms(INITIAL_PAGE)
        .subscribeOn(subscribeOn)
        .observeOn(observeOn)
        .subscribe({

        }, {
          view.showInfo("Error getting service:" + it.localizedMessage)
        })
  }
}