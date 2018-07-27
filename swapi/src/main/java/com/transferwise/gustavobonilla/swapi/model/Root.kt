/*
 * Copyright (c) 2018 Gustavo E Bonilla - TransferWise Tech Test
 *
 * StarWarsTransferWiseTechTest
 * Root.kt
 *
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 25, 2018
 */

package com.transferwise.gustavobonilla.swapi.model

import com.google.gson.annotations.SerializedName

class Root(
  @SerializedName("films")
  val filmsUrl: String?,
  @SerializedName("people")
  val peopleUrl: String?,
  @SerializedName("planets")
  val planetsUrl: String?,
  @SerializedName("species")
  val speciesUrl: String?,
  @SerializedName("starships")
  val starshipsUrl: String?,
  @SerializedName("vehicles")
  val vehiclesUrl: String?)