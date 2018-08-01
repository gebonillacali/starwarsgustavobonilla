/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * StarWarsApplicationComponent.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.di

import android.content.Context
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.MainActivity
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.StarWarsApplication
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.detailinfo.di.DetailComponent
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.detailinfo.di.DetailModule
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.home.di.HomeComponent
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.home.di.HomeModule
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.sectioninfo.di.SectionComponent
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.sectioninfo.di.SectionModule
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component (modules = [StarWarsApplicationModule::class, StarWarsRepositoryModule::class])
interface StarWarsApplicationComponent {

  fun plus(homeModule: HomeModule): HomeComponent
  fun plus(sectionModule: SectionModule): SectionComponent
  fun plus(detailModule: DetailModule): DetailComponent
  fun inject(activity: MainActivity)
}

@Module
class StarWarsApplicationModule(private val starWarsApp: StarWarsApplication) {
  @Provides
  @Singleton
  fun providesContext(): Context {
    return starWarsApp
  }

  @Provides
  @Singleton
  fun providesApplication(): StarWarsApplication {
    return starWarsApp
  }
}