/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * Species.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.swapi.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "species")
data class Species(
  val name: String?,
  val classification: String?,
  val designation: String?,

  @SerializedName("average_height")
  val averageHeight: String?,

  @SerializedName("average_lifespan")
  val averageLifespan: String?,

  @SerializedName("eye_colors")
  val eyeColors: String?,

  @SerializedName("hair_colors")
  val hairColors: String?,

  @SerializedName("skin_colors")
  val skinColors: String?,

  @SerializedName("homeworld")
  val homeWorld: String?,

  val language: String?,
  val created: String?,
  val edited: String?,

  @PrimaryKey
  val url: String,

  @SerializedName("people")
  val peopleUrls: List<String>?,

  @SerializedName("films")
  val filmsUrls: List<String>?): StarWarsModel() {
  override fun getItemUrl(): String = url
}