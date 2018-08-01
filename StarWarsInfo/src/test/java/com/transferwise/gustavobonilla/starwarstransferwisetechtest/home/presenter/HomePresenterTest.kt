/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * HomePresenterTest.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.home.presenter

import com.transferwise.gustavobonilla.starwarstransferwisetechtest.ModelCreator
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.home.Home
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

class HomePresenterTest {

  companion object {
    private const val DELAY_TIME_MS = 500L
  }

  private val testScheduler = TestScheduler()

  @Mock
  lateinit var repository: ItemRepository

  @Mock
  lateinit var view: Home.View

  lateinit var presenter: Home.Presenter

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
    presenter = createPresenter()
  }

  @Test
  fun init() {
    given(repository.getRoot())
        .willReturn(Observable.just(ModelCreator.homeModel))
    presenter.init()

    testScheduler.advanceTimeBy(DELAY_TIME_MS, TimeUnit.MILLISECONDS)
    verify(view, atLeastOnce()).setAdapterItems(anyList())
  }

  private fun createPresenter(): Home.Presenter {
    return HomePresenter(
        view,
        repository,
        subscribeOn = Schedulers.trampoline(),
        observeOn = Schedulers.trampoline())
  }
}