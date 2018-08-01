/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * SectionInjection.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.sectioninfo.di

import com.transferwise.gustavobonilla.starwarstransferwisetechtest.sectioninfo.Section
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.sectioninfo.presenter.SectionPresenter
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.sectioninfo.view.SectionFragment
import com.transferwise.gustavobonilla.swapi.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [SectionModule::class])
interface SectionComponent {
  fun inject(view: SectionFragment)
}

@Module
class SectionModule(val view: Section.View, private val page: String) {
  @Provides
  fun provideSectionPresenter(repository: ItemRepository): Section.Presenter {
    return SectionPresenter(repository, view, page)
  }
}