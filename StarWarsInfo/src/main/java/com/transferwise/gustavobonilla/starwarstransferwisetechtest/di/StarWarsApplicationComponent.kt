/*
 * Copyright (c) 2018 Gustavo E Bonilla - TransferWise Tech Test
 *
 * StarWarsTransferWiseTechTest
 * StartWarsApplicationComponent.kt
 *
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 26, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.di

import android.app.Application
import android.content.Context
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.MainActivity
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.home.di.HomeComponent
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.home.di.HomeModule
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.home.view.HomeFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component (modules = [StarWarsApplicationModule::class, StarWarsApiModule::class])
interface StarWarsApplicationComponent {

  fun plus(homeModule: HomeModule): HomeComponent
  fun inject(activity: MainActivity)
}

@Module
class StarWarsApplicationModule(private val starWarsApp: Application) {
  @Provides
  @Singleton
  fun providesContext(): Context {
    return starWarsApp
  }
}