/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * PlanetDao.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.swapi.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.transferwise.gustavobonilla.swapi.model.Film
import com.transferwise.gustavobonilla.swapi.model.Planet
import io.reactivex.Single

@Dao
interface PlanetDao{
  companion object {
    const val GET_ALL_QUERY = "select * from planets"
    const val GET_QUERY = "select * from planets where url = :url"
  }

  @Query(GET_ALL_QUERY)
  fun getAll(): Single<List<Planet>>

  @Query(GET_QUERY)
  fun get(url: String): Single<Planet>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(planet: Planet)

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insertAll(planets: List<Planet>)
}