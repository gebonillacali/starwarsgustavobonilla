/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * Planet.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.swapi.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "planets")
data class Planet(
  val name: String?,
  val diameter: String?,
  val gravity: String?,
  val population: String?,
  val climate: String?,
  val terrain: String?,
  val created: String?,
  val edited: String?,

  @PrimaryKey
  val url: String,

  @SerializedName("rotation_period")
  val rotationPeriod: String?,

  @SerializedName("orbital_period")
  val orbitalPeriod: String?,

  @SerializedName("surface_water")
  val surfaceWater: String?,

  @SerializedName("residents")
  val residentsUrls: List<String>,

  @SerializedName("films")
  val filmsUrls: List<String>): StarWarsModel() {
  override fun getItemUrl(): String = url
}