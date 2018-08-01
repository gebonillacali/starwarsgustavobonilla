/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * SectionPresenter.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.sectioninfo.presenter

import com.transferwise.gustavobonilla.starwarstransferwisetechtest.sectioninfo.Section
import com.transferwise.gustavobonilla.swapi.model.StarWarsModel
import com.transferwise.gustavobonilla.swapi.repository.ItemRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.atomic.AtomicBoolean

class SectionPresenter(
    val repository: ItemRepository,
    val view: Section.View,
    val page: String,
    private val subscribeOn: Scheduler = Schedulers.io(),
    private val observeOn: Scheduler = AndroidSchedulers.mainThread()
): Section.Presenter {

  private var pageNumber = 1
  private var isRequestingData = AtomicBoolean(false)
  private var disposables = CompositeDisposable()

  //region Section.Presenter
  override fun init() {
    loadSection()
  }

  override fun requestMoreItems() {
    if (!isRequestingData.get()) {
      pageNumber++
      loadSection()
    }
  }
  //endregion

  //region Lifecycle.Callback
  override fun onDestroy() {
    disposables.dispose()
  }
  //endregion

  //region private

  /**
   * Request and load the section items.
   */
  private fun loadSection() {
    if (!isRequestingData.getAndSet(true)) {
      if (view.getAdapterItemCount() <= 0) {
        view.showError()
      }
      val disposable = selectorSection()
          .subscribeOn(subscribeOn)
          .observeOn(observeOn)
          .subscribe({
            if (it.isNotEmpty() && view.getAdapterItemCount() <= 0) {
              view.setAdapterItems(it as List<StarWarsModel>)
              isRequestingData.set(false)
              view.hideError()
            }
          }, {
            isRequestingData.set(false)
            view.showError()
          })
      disposables.add(disposable)
    }
  }

  /**
   * Selects the section [Observable] object for a specific section.
   */
  private fun selectorSection(): Observable<List<StarWarsModel>> {
    return when (page) {
      "Films" -> repository.getAllFilms(pageNumber)
      "People" -> repository.getAllPeople(pageNumber)
      "Planets" -> repository.getAllPlanet(pageNumber)
      "Species" -> repository.getAllSpecies(pageNumber)
      "Starships" -> repository.getAllStarShip(pageNumber)
      "Vehicles" -> repository.getAllVehicle(pageNumber)
      else -> Observable.just(listOf())
    } as Observable<List<StarWarsModel>>
  }
  //endregion
}