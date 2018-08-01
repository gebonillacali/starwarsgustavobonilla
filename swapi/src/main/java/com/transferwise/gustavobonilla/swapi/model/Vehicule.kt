/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * Vehicule.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.swapi.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "vehicules")
class Vehicle(
    var name: String? = null,
    var model: String? = null,

    @SerializedName("vehicle_class")
    var vehicleClass: String? = null,

    var manufacturer: String? = null,

    @SerializedName("cost_in_credits")
    var costInCredits: String? = null,

    var length: String? = null,
    var crew: String? = null,
    var passengers: String? = null,

    @SerializedName("max_atmosphering_speed")
    var maxAtmospheringSpeed: String? = null,

    @SerializedName("cargo_capacity")
    var cargoCapacity: String? = null,

    var consumables: String? = null,
    var created: String? = null,
    var edited: String? = null,

    @PrimaryKey
    var url: String,

    @SerializedName("pilots")
    var pilotsUrls: List<String>? = null,

    @SerializedName("films")
    var filmsUrls: List<String>? = null): StarWarsModel() {
  override fun getItemUrl(): String = url
}