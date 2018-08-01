/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * DetailPresenterTest.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.detailinfo.presenter

import com.transferwise.gustavobonilla.starwarstransferwisetechtest.ModelCreator
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.detailinfo.Detail
import com.transferwise.gustavobonilla.swapi.repository.ItemRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.atLeastOnce
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeUnit

class DetailPresenterTest {

  companion object {
    private const val DELAY_TIME_MS = 500L
  }

  private val testScheduler = TestScheduler()

  @Mock
  lateinit var repository: ItemRepository

  @Mock
  lateinit var view: Detail.View

  lateinit var presenter: Detail.Presenter

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
  }

  @Test
  fun loadFilmData() {
    given(repository.getFilm(1, "https://swapi.co/api/films/1/"))
        .willReturn(Observable.just(ModelCreator.detailFilm))
    presenter = createPresenter(1, "https://swapi.co/api/films/1/")
    presenter.initialize()
    testScheduler.advanceTimeBy(DetailPresenterTest.DELAY_TIME_MS, TimeUnit.MILLISECONDS)
    verify(view, atLeastOnce()).populateFilmInfo(ModelCreator.detailFilm)
  }

  @Test
  fun loadPeopleData() {
    given(repository.getPeople(1, "https://swapi.co/api/people/1/"))
        .willReturn(Observable.just(ModelCreator.detailPeople))
    presenter = createPresenter(1, "https://swapi.co/api/people/1/")
    presenter.initialize()
    testScheduler.advanceTimeBy(DetailPresenterTest.DELAY_TIME_MS, TimeUnit.MILLISECONDS)
    verify(view, atLeastOnce()).populatePeopleInfo(ModelCreator.detailPeople)
  }

  @Test
  fun loadStarshipData() {
    given(repository.getStarShip(9, "https://swapi.co/api/starships/9/"))
        .willReturn(Observable.just(ModelCreator.detailStarship))
    presenter = createPresenter(9, "https://swapi.co/api/starships/9/")
    presenter.initialize()
    testScheduler.advanceTimeBy(DetailPresenterTest.DELAY_TIME_MS, TimeUnit.MILLISECONDS)
    verify(view, atLeastOnce()).populateStarshipInfo(ModelCreator.detailStarship)
  }

  @Test
  fun loadVehicleData() {
    given(repository.getVehicle(4, "https://swapi.co/api/vehicles/4/"))
        .willReturn(Observable.just(ModelCreator.detailVehicle))
    presenter = createPresenter(4, "https://swapi.co/api/vehicles/4/")
    presenter.initialize()
    testScheduler.advanceTimeBy(DetailPresenterTest.DELAY_TIME_MS, TimeUnit.MILLISECONDS)
    verify(view, atLeastOnce()).populateVehicleInfo(ModelCreator.detailVehicle)
  }

  @Test
  fun loadSpeciesData() {
    given(repository.getSpecies(3, "https://swapi.co/api/species/3/"))
        .willReturn(Observable.just(ModelCreator.detailSpecies))
    presenter = createPresenter(3, "https://swapi.co/api/species/3/")
    presenter.initialize()
    testScheduler.advanceTimeBy(DetailPresenterTest.DELAY_TIME_MS, TimeUnit.MILLISECONDS)
    verify(view, atLeastOnce()).populateSpeciesInfo(ModelCreator.detailSpecies)
  }

  @Test
  fun loadPlanetData() {
    given(repository.getPlanet(1, "https://swapi.co/api/planets/1/"))
        .willReturn(Observable.just(ModelCreator.detailPlanet))
    presenter = createPresenter(1, "https://swapi.co/api/planets/1/")
    presenter.initialize()
    testScheduler.advanceTimeBy(DetailPresenterTest.DELAY_TIME_MS, TimeUnit.MILLISECONDS)
    verify(view, atLeastOnce()).populatePlanetInfo(ModelCreator.detailPlanet)
  }

  private fun createPresenter(id: Int, url: String): Detail.Presenter {
    return DetailPresenter(
        repository,
        view,
        id.toString(),
        url,
        subscribeOn = Schedulers.trampoline(),
        observeOn = Schedulers.trampoline())
  }


}