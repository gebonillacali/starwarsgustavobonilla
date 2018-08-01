/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * StarwarsTypeConverter.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.swapi.dao

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.transferwise.gustavobonilla.swapi.fromJson
import java.util.Collections

class StarwarsTypeConverter {

  @TypeConverter
  fun stringToStringList(data: String): List<String> {
    if (data.isNotEmpty()) {
      return Gson().fromJson<List<String>>(data)
    }
    return Collections.emptyList()
  }

  @TypeConverter
  fun stringListToString(list: List<String>): String {
    return Gson().toJson(list)
  }

}