/*
 * Copyright (c) 2018 Gustavo E Bonilla - TransferWise Tech Test
 *
 * StarWarsTransferWiseTechTest
 * StarShip.kt
 *
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 25, 2018
 */

package com.transferwise.gustavobonilla.swapi.model

import com.google.gson.annotations.SerializedName

data class Starship(

  @SerializedName("starship_class")
  val starshipClass: String?,

  @SerializedName("hyperdrive_rating")
  val hyperdriveRating: String?,

  @SerializedName("MGLT")
  val mglt: String?): Vehicle()