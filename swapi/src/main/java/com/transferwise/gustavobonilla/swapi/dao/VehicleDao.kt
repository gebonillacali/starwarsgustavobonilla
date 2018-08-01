/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * VehicleDao.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.swapi.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.transferwise.gustavobonilla.swapi.model.Starship
import com.transferwise.gustavobonilla.swapi.model.Vehicle
import io.reactivex.Single

@Dao
interface VehicleDao{
  companion object {
    const val GET_ALL_QUERY = "select * from vehicules"
    const val GET_QUERY = "select * from vehicules where url = :url"
  }

  @Query(GET_ALL_QUERY)
  fun getAll(): Single<List<Vehicle>>

  @Query(GET_QUERY)
  fun get(url: String): Single<Vehicle>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(vehicle: Vehicle)

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insertAll(vehicles: List<Vehicle>)
}