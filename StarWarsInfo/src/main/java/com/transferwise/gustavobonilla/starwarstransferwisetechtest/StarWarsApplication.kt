/*
 * Copyright (c) 2018 Gustavo E Bonilla - TransferWise Tech Test
 *
 * StarWarsTransferWiseTechTest
 * StarWarsApplication.kt
 *
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 26, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest

import android.app.Application
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.di.DaggerStarWarsApplicationComponent
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.di.StarWarsApiModule
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.di.StarWarsApplicationComponent
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.di.StarWarsApplicationModule

class StarWarsApplication: Application() {
  lateinit var starWarsApplicationComponent: StarWarsApplicationComponent

  override fun onCreate() {
    super.onCreate()
    starWarsApplicationComponent = initDagger(this)
  }

  private fun initDagger(app: StarWarsApplication): StarWarsApplicationComponent {
    return DaggerStarWarsApplicationComponent.builder()
        .starWarsApplicationModule(StarWarsApplicationModule(app))
        .starWarsApiModule(StarWarsApiModule())
        .build()
  }
}