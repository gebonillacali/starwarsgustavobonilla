/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * SpeciesDao.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.swapi.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.transferwise.gustavobonilla.swapi.model.Planet
import com.transferwise.gustavobonilla.swapi.model.Species
import io.reactivex.Single

@Dao
interface SpeciesDao{
  companion object {
    const val GET_ALL_QUERY = "select * from species"
    const val GET_QUERY = "select * from species where url = :url"
  }

  @Query(GET_ALL_QUERY)
  fun getAll(): Single<List<Species>>

  @Query(GET_QUERY)
  fun get(url: String): Single<Species>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(species: Species)

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insertAll(species: List<Species>)
}