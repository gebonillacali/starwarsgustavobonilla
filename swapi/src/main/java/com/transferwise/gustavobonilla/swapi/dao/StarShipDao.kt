/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * StarShipDao.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.swapi.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.transferwise.gustavobonilla.swapi.model.Species
import com.transferwise.gustavobonilla.swapi.model.Starship
import io.reactivex.Single

@Dao
interface StarShipDao{
  companion object {
    const val GET_ALL_QUERY = "select * from starships"
    const val GET_QUERY = "select * from starships where url = :url"
  }

  @Query(GET_ALL_QUERY)
  fun getAll(): Single<List<Starship>>

  @Query(GET_QUERY)
  fun get(url: String): Single<Starship>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(starship: Starship)

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insertAll(starships: List<Starship>)
}