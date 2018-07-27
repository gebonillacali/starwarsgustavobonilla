/*
 * Copyright (c) 2018 Gustavo E Bonilla - TransferWise Tech Test
 *
 * StarWarsTransferWiseTechTest
 * Film.kt
 *
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 25, 2018
 */

package com.transferwise.gustavobonilla.swapi.model

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "film")
data class Film(
    val title: String?,

    @SerializedName("episode_id")
    val episodeId: String,

    @SerializedName("opening_crawl")
    val openingCrawl: String,

    val director: String,
    val producer: String,
    val url: String,
    val created: String,
    val edited: String,

    @SerializedName("species")
    val speciesUrls: List<String>,

    @SerializedName("starships")
    val starshipsUrls: List<String>,

    @SerializedName("vehicles")
    val vehiclesUrls: List<String>,

    @SerializedName("planets")
    val planetsUrls: List<String>,

    @SerializedName("characters")
    val charactersUrls: List<String>): StarWarsModel() {

    override fun getItemUrl(): String = url
}