/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * DetailInjection.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.detailinfo.di

import com.transferwise.gustavobonilla.starwarstransferwisetechtest.detailinfo.Detail
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.detailinfo.presenter.DetailPresenter
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.detailinfo.view.DetailFragment
import com.transferwise.gustavobonilla.swapi.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [DetailModule::class])
interface DetailComponent {
  fun inject(view: DetailFragment)
}

@Module
class DetailModule(val view: Detail.View, private val id: String, private val url: String) {
  @Provides
  fun provideSectionPresenter(repository: ItemRepository): Detail.Presenter {
    return DetailPresenter(repository, view, id, url)
  }
}