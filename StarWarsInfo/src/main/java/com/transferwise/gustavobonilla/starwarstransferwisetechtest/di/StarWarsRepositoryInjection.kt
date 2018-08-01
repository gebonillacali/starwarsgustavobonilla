/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * StarWarsRepositoryInjection.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.di

import android.arch.persistence.room.Room
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.StarWarsApplication
import com.transferwise.gustavobonilla.swapi.api.StarWarsApi
import com.transferwise.gustavobonilla.swapi.dao.AppDatabase
import com.transferwise.gustavobonilla.swapi.repository.ItemRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StarWarsRepositoryModule {

  @Provides
  @Singleton
  fun provideStarWarsRepository(api: StarWarsApi, database: AppDatabase): ItemRepository {
    return ItemRepository(api.api(), database)
  }

  @Provides
  @Singleton
  fun provideStarWarsApi(): StarWarsApi {
    return StarWarsApi()
  }

  @Provides
  @Singleton
  fun provideStarWarsDatabase(application: StarWarsApplication): AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, "starwars-database").allowMainThreadQueries().build()
  }
}