/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * FilmDao.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.swapi.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.transferwise.gustavobonilla.swapi.model.Film
import io.reactivex.Single

@Dao
interface FilmDao{
  companion object {
    const val GET_ALL_QUERY = "select * from films"
    const val GET_QUERY = "select * from films where url = :url"
  }

  @Query(GET_ALL_QUERY)
  fun getAll(): Single<List<Film>>

  @Query(GET_QUERY)
  fun get(url: String): Single<Film>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(film: Film)

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insertAll(films: List<Film>)
}