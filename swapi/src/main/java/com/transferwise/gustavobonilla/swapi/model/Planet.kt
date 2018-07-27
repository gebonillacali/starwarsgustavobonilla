/*
 * Copyright (c) 2018 Gustavo E Bonilla - TransferWise Tech Test
 *
 * StarWarsTransferWiseTechTest
 * Planet.kt
 *
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 25, 2018
 */

package com.transferwise.gustavobonilla.swapi.model

import com.google.gson.annotations.SerializedName

data class Planet(
  val name: String?,
  val diameter: String?,
  val gravity: String?,
  val population: String?,
  val climate: String?,
  val terrain: String?,
  val created: String?,
  val edited: String?,
  val url: String,

  @SerializedName("rotation_period")
  val rotationPeriod: String?,

  @SerializedName("orbital_period")
  val orbitalPeriod: String?,

  @SerializedName("surface_water")
  val surfaceWater: String?,

  @SerializedName("residents")
  val residentsUrls: List<String>?,

  @SerializedName("films")
  val filmsUrls: List<String>?): StarWarsModel() {
  override fun getItemUrl(): String = url
}