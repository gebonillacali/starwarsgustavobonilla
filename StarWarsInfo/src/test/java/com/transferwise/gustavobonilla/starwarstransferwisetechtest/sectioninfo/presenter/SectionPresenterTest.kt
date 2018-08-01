/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * SectionPresenterTest.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.sectioninfo.presenter

import com.transferwise.gustavobonilla.starwarstransferwisetechtest.ModelCreator
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.sectioninfo.Section
import com.transferwise.gustavobonilla.swapi.repository.ItemRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.anyList
import org.mockito.BDDMockito.atLeastOnce
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeUnit

class SectionPresenterTest {
  companion object {
    private const val DELAY_TIME_MS = 500L
  }

  private val testScheduler = TestScheduler()

  @Mock
  lateinit var repository: ItemRepository

  @Mock
  lateinit var view: Section.View

  @Mock
  lateinit var presenter: Section.Presenter

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
  }

  @Test
  fun loadAllFilmsSection() {
    given(repository.getAllFilms(1))
        .willReturn(Observable.just(ModelCreator.sectionAllFilmsModel))
    presenter = createPresenter("Films")
    presenter.init()
    testScheduler.advanceTimeBy(SectionPresenterTest.DELAY_TIME_MS, TimeUnit.MILLISECONDS)
    verify(view, atLeastOnce()).setAdapterItems(anyList())
  }

  @Test
  fun loadAllPeopleSection() {
    given(repository.getAllPeople(1))
        .willReturn(Observable.just(ModelCreator.sectionAllPeopleModel))
    presenter = createPresenter("People")
    presenter.init()
    testScheduler.advanceTimeBy(SectionPresenterTest.DELAY_TIME_MS, TimeUnit.MILLISECONDS)
    verify(view, atLeastOnce()).setAdapterItems(anyList())
  }

  @Test
  fun loadAllPlanetsSection() {
    given(repository.getAllPlanet(1))
        .willReturn(Observable.just(ModelCreator.sectionAllPlanetsModel))
    presenter = createPresenter("Planets")
    presenter.init()
    testScheduler.advanceTimeBy(SectionPresenterTest.DELAY_TIME_MS, TimeUnit.MILLISECONDS)
    verify(view, atLeastOnce()).setAdapterItems(anyList())
  }

  @Test
  fun loadAllSpeciesSection() {
    given(repository.getAllSpecies(1))
        .willReturn(Observable.just(ModelCreator.sectionAllSpecies))
    presenter = createPresenter("Species")
    presenter.init()
    testScheduler.advanceTimeBy(SectionPresenterTest.DELAY_TIME_MS, TimeUnit.MILLISECONDS)
    verify(view, atLeastOnce()).setAdapterItems(anyList())
  }

  @Test
  fun loadAllStarshipsSection() {
    given(repository.getAllStarShip(1))
        .willReturn(Observable.just(ModelCreator.sectionAllStarship))
    presenter = createPresenter("Starships")
    presenter.init()
    testScheduler.advanceTimeBy(SectionPresenterTest.DELAY_TIME_MS, TimeUnit.MILLISECONDS)
    verify(view, atLeastOnce()).setAdapterItems(anyList())
  }

  @Test
  fun loadAllVehicleSection() {
    given(repository.getAllVehicle(1))
        .willReturn(Observable.just(ModelCreator.sectionAllVehicle))
    presenter = createPresenter("Vehicles")
    presenter.init()
    testScheduler.advanceTimeBy(SectionPresenterTest.DELAY_TIME_MS, TimeUnit.MILLISECONDS)
    verify(view, atLeastOnce()).setAdapterItems(anyList())
  }

  private fun createPresenter(page: String): Section.Presenter {
    return SectionPresenter(
        repository,
        view,
        page,
        subscribeOn = Schedulers.trampoline(),
        observeOn = Schedulers.trampoline())
  }
}