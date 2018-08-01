/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * RootDao.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.swapi.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.transferwise.gustavobonilla.swapi.model.Root
import io.reactivex.Single

@Dao
interface RootDao{
  companion object {
    const val GET_QUERY = "select * from root limit 1"
  }

  @Query(GET_QUERY)
  fun get(): Single<Root>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(root: Root)
}