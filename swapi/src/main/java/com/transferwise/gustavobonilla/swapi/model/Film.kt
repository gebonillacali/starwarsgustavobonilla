/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * Film.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.swapi.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "films")
data class Film(
    val title: String?,

    @SerializedName("episode_id")
    val episodeId: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("opening_crawl")
    val openingCrawl: String,

    val director: String,
    val producer: String,
    @PrimaryKey
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

    /**
     * Gets the image for a certain Film.
     * Note: due to the lack of images in the swapi.co (Starwars API), I've provide this method to
     * demonstrate the use of [Glide] in a project for url images loading.
     * @see https://swapi.co/documentation
     */
    fun getUrlImageFilm(): String {
        return when (episodeId) {
            "1" -> "https://lh3.googleusercontent.com/8Y6UWUacRmj0YYBAO8s-6vvcPopquaCgQfW83sI8Z5QTnQRVXQlyQCRVWLLeGriyyKxo=w200-h300"
            "2" -> "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1386386226i/19250549._UY200_.jpg"
            "3" -> "https://farm8.staticflickr.com/7667/17116729200_e73db7635b_o.jpg"
            "4" -> "https://i2.wp.com/disneynews.us/wp-content/uploads/2018/04/l6034_6046_Star-Wars-A-New-Hope.jpg?resize=200%2C200&ssl=1"
            "5" -> "https://d2e111jq13me73.cloudfront.net/sites/default/files/styles/product_image_aspect_switcher_170w/public/product-images/csm-movie/empire-strikes-back-poster-big.jpg?itok=62xyT7Sz"
            "6" -> "https://i.ebayimg.com/images/g/8WoAAOSwUchaNmaj/s-l300.jpg"
            "7" -> "https://images.pristineauction.com/94/945175/thumb_1-JJ-Abrams-Signed-12x18-Star-Wars-The-Force-Awakens-Movie-Vertical-Poster-Signed-in-GoldBeckett-Auth-PristineAuction.com.jpg"
            else -> ""
        }
    }

    /**
     * Gets the Hero image for a certain Film.
     * Note: due to the lack of images in the swapi.co (Starwars API), I've provide this method to
     * demonstrate the use of [Glide] in a project for url images loading.
     * @see https://swapi.co/documentation
     */
    fun getUrlHeroImageFilm(): String {
        return when (episodeId) {
            "1" -> "https://lumiere-a.akamaihd.net/v1/images/Star-Wars-Phantom-Menace-I-Poster_f5832812.jpeg?region=0%2C241%2C678%2C339"
            "2" -> "https://lumiere-a.akamaihd.net/v1/images/Star-Wars-Attack-Clones-II-Poster_53baa2e7.jpeg?region=0%2C136%2C678%2C339"
            "3" -> "https://lumiere-a.akamaihd.net/v1/images/Star-Wars-Revenge-Sith-III-Poster_646108ce.jpeg?region=61%2C329%2C634%2C319"
            "4" -> "https://lumiere-a.akamaihd.net/v1/images/Star-Wars-New-Hope-IV-Poster_c217085b.jpeg?region=46%2C333%2C580%2C290"
            "5" -> "https://lumiere-a.akamaihd.net/v1/images/Star-Wars-Empire-Strikes-Back-V-Poster_878f7fce.jpeg?region=31%2C272%2C603%2C302"
            "6" -> "https://lumiere-a.akamaihd.net/v1/images/Star-Wars-Return-Jedi-VI-Poster_a10501d2.jpeg?region=40%2C244%2C593%2C296"
            "7" -> "https://lumiere-a.akamaihd.net/v1/images/avco_payoff_1-sht_v7_lg_32e68793.jpeg?region=118%2C252%2C1384%2C696&width=960"
            else -> "https://lumiere-a.akamaihd.net/v1/images/avco_payoff_1-sht_v7_lg_32e68793.jpeg?region=118%2C252%2C1384%2C696&width=960"
        }
    }
}