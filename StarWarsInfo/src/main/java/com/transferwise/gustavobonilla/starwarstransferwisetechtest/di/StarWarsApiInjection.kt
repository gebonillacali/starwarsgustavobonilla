/*
 * Copyright (c) 2018 Gustavo E Bonilla - TransferWise Tech Test
 *
 * StarWarsTransferWiseTechTest
 * StarWarsApiInjection.kt
 *
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 26, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.di

import com.transferwise.gustavobonilla.swapi.api.StarWarsApi
import dagger.Module
import dagger.Provides
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope
import javax.inject.Singleton

@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class ActivityScope

@Module
class StarWarsApiModule {

  @Provides
  @Singleton
  fun provideStarWarsApi(): StarWarsApi {
    return StarWarsApi()
  }
}