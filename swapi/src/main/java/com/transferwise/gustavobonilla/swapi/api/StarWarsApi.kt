/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * StarWarsApi.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.swapi.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class StarWarsApi {

  private val startWarsApi: StarWars

  init {
    startWarsApi = create()
  }

  companion object {
    private const val API_URL = "https://swapi.co/"

    /**
     * Create the instance of [Retrofit] for the [StarWars] endpoint.
     */
    fun create(): StarWars {
      val retrofit = Retrofit.Builder()
          .client(OkHttpClient())
          .addCallAdapterFactory(
              RxJava2CallAdapterFactory.create())
          .addConverterFactory(
             GsonConverterFactory.create())
          .baseUrl(API_URL)
          .build()

      return retrofit.create(StarWars::class.java)
    }
  }

  /**
   * Gets the instance of the [StarWarsApi] API.
   */
  fun api(): StarWars {
    return startWarsApi
  }
}