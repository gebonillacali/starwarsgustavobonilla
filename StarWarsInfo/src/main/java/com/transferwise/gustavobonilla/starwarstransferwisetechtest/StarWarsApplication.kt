/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * StarWarsApplication.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest

import android.app.Application
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.di.DaggerStarWarsApplicationComponent
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.di.StarWarsApplicationComponent
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.di.StarWarsApplicationModule
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.di.StarWarsRepositoryModule

class StarWarsApplication: Application() {
  lateinit var starWarsApplicationComponent: StarWarsApplicationComponent

  override fun onCreate() {
    super.onCreate()
    starWarsApplicationComponent = initDagger(this)
  }

  /**
   * Inits the Dependency inject for the app.
   *
   * @param app the instance of the application.
   */
  private fun initDagger(app: StarWarsApplication): StarWarsApplicationComponent {
    return DaggerStarWarsApplicationComponent.builder()
        .starWarsApplicationModule(StarWarsApplicationModule(app))
        .starWarsRepositoryModule(StarWarsRepositoryModule())
        .build()
  }
}