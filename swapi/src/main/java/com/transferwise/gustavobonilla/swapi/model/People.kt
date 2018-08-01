/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * People.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.swapi.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "people")
data class People(

  val name: String?,

  @SerializedName("birth_year")
  val birthYear: String?,

  val gender: String?,

  @SerializedName("hair_color")
  val hairColor: String?,

  val height: String?,

  @SerializedName("homeworld")
  val homeWorldUrl: String?,

  val mass: String?,

  @SerializedName("skin_color")
  val skinColor: String?,

  val created: String?,
  val edited: String?,

  @PrimaryKey
  val url: String,

  @SerializedName("films")
  val filmsUrls: List<String>,

  @SerializedName("species")
  val speciesUrls: List<String>?,

  @SerializedName("starships")
  val starshipsUrls: List<String>?,

  @SerializedName("vehicles")
  val vehiclesUrls: List<String>?): StarWarsModel() {

  override fun getItemUrl(): String = url
}