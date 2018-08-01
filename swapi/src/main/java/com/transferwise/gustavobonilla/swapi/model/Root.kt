/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * Root.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.swapi.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "root")
class Root(
  @PrimaryKey(autoGenerate = true)
  val idRoot: Int,
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
  val vehiclesUrl: String?): StarWarsModel() {
  val filmTitle: String
    get() = filmsUrl?.split("/")?.filter { it.isNotEmpty() }?.last()?.capitalize() ?: ""
  val peopleTitle: String
    get() = peopleUrl?.split("/")?.filter { it.isNotEmpty() }?.last()?.capitalize() ?: ""
  val planetTitle: String
    get() = planetsUrl?.split("/")?.filter { it.isNotEmpty() }?.last()?.capitalize() ?: ""
  val speciesTitle: String
    get() = speciesUrl?.split("/")?.filter { it.isNotEmpty() }?.last()?.capitalize() ?: ""
  val starshipsTitle: String
    get() = starshipsUrl?.split("/")?.filter { it.isNotEmpty() }?.last()?.capitalize() ?: ""
  val vehiclesTitle: String
    get() = vehiclesUrl?.split("/")?.filter { it.isNotEmpty() }?.last()?.capitalize() ?: ""

  override fun getItemUrl(): String {
    return ""
  }

  /**
   * Gets the [RootItem] object used in home screen.
   *
   * @param listDrawable the list of images resources to build a [RootItem].
   */
  fun getDataAsList(listDrawable: List<Int>): List<RootItem> {
    return listOf(
        RootItem(filmTitle, listDrawable[0], filmsUrl!!),
        RootItem(peopleTitle, listDrawable[1], peopleUrl!!),
        RootItem(planetTitle, listDrawable[2], planetsUrl!!),
        RootItem(speciesTitle, listDrawable[3], speciesUrl!!),
        RootItem(starshipsTitle, listDrawable[4], starshipsUrl!!),
        RootItem(vehiclesTitle, listDrawable[5], vehiclesUrl!!))
  }
}