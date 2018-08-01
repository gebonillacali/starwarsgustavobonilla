/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * HomeInjection.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.home.di

import com.transferwise.gustavobonilla.starwarstransferwisetechtest.home.Home
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.home.presenter.HomePresenter
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.home.view.HomeFragment
import com.transferwise.gustavobonilla.swapi.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent (modules = [HomeModule::class])
interface HomeComponent {
  fun inject(view: HomeFragment)
}

@Module
class HomeModule(
    private val view: Home.View) {

  @Provides
  fun provideHomePresenter(repository: ItemRepository): Home.Presenter {
    return HomePresenter(view, repository)
  }
}