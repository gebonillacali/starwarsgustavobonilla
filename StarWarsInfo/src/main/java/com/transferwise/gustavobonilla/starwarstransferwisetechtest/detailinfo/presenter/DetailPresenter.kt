/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * DetailPresenter.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.detailinfo.presenter

import com.transferwise.gustavobonilla.starwarstransferwisetechtest.detailinfo.Detail
import com.transferwise.gustavobonilla.swapi.repository.ItemRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailPresenter(
    val repository: ItemRepository,
    val view: Detail.View,
    val id: String,
    val url: String,
    private val subscribeOn: Scheduler = Schedulers.io(),
    private val observeOn: Scheduler = AndroidSchedulers.mainThread()
): Detail.Presenter {

  private var disposables = CompositeDisposable()

  //region Detail.Presenter
  override fun initialize() {
    loadData()
  }
  //endregion

  //region LifeCycle.Callback
  override fun onDestroy() {
    disposables.dispose()
  }
  //endregion

  //region private
  /**
   * Request and loads the data into the view depending the kind of data.
   */
  private fun loadData() {
    view.showError()
    when {
      url.contains("films") -> loadFilmData()
      url.contains("people") -> loadPeopleData()
      url.contains("starships") -> loadStarshipData()
      url.contains("vehicles") -> loadVehicleData()
      url.contains("species") -> loadSpeciesData()
      url.contains("planets") -> loadPlanetData()
    }
  }

  /**
   * Request and loads the film data into the view.
   */
  private fun loadFilmData() {
    val disposable = repository.getFilm(id.toInt(), url)
        .subscribeOn(subscribeOn)
        .observeOn(observeOn)
        .subscribe ({
          view.populateFilmInfo(it)
          view.hideError()
        },{
          view.hideError()
        })
    disposables.add(disposable)
  }

  /**
   * Request and loads the people data into the view.
   */
  private fun loadPeopleData() {
    val disposable = repository.getPeople(id.toInt(), url)
        .subscribeOn(subscribeOn)
        .observeOn(observeOn)
        .subscribe ({
          view.populatePeopleInfo(it)
          view.hideError()
        },{
          view.hideError()
        })
    disposables.add(disposable)
  }

  /**
   * Request and loads the Starship data into the view.
   */
  private fun loadStarshipData() {
    val disposable = repository.getStarShip(id.toInt(), url)
        .subscribeOn(subscribeOn)
        .observeOn(observeOn)
        .subscribe ({
          view.populateStarshipInfo(it)
          view.hideError()
        },{
          view.hideError()
        })
    disposables.add(disposable)
  }

  /**
   * Request and loads the Vehicle data into the view.
   */
  private fun loadVehicleData() {
    val disposable = repository.getVehicle(id.toInt(), url)
        .subscribeOn(subscribeOn)
        .observeOn(observeOn)
        .subscribe ({
          view.populateVehicleInfo(it)
          view.hideError()
        },{
          view.hideError()
        })
    disposables.add(disposable)
  }

  /**
   * Request and loads the Species data into the view.
   */
  private fun loadSpeciesData() {
    val disposable = repository.getSpecies(id.toInt(), url)
        .subscribeOn(subscribeOn)
        .observeOn(observeOn)
        .subscribe ({
          view.populateSpeciesInfo(it)
          view.hideError()
        },{
          view.hideError()
        })
    disposables.add(disposable)
  }

  /**
   * Request and loads the Planet data into the view.
   */
  private fun loadPlanetData() {
    val disposable = repository.getPlanet(id.toInt(), url)
        .subscribeOn(subscribeOn)
        .observeOn(observeOn)
        .subscribe ({
          view.populatePlanetInfo(it)
          view.hideError()
        },{
          view.hideError()
        })
    disposables.add(disposable)
  }
  //endregion
}