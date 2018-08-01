/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * PeopleDao.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.swapi.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.transferwise.gustavobonilla.swapi.model.People
import io.reactivex.Single

@Dao
interface PeopleDao{
  companion object {
    const val GET_ALL_QUERY = "select * from people"
    const val GET_QUERY = "select * from people where url = :url"
  }

  @Query(GET_ALL_QUERY)
  fun getAll(): Single<List<People>>

  @Query(GET_QUERY)
  fun get(url: String): Single<People>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(people: People)

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insertAll(peopleList: List<People>)
}