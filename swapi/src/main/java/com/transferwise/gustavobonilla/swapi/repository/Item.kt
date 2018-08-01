/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * Item.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.swapi.repository

import com.transferwise.gustavobonilla.swapi.model.Film
import com.transferwise.gustavobonilla.swapi.model.People
import com.transferwise.gustavobonilla.swapi.model.Planet
import com.transferwise.gustavobonilla.swapi.model.Root
import com.transferwise.gustavobonilla.swapi.model.Species
import com.transferwise.gustavobonilla.swapi.model.Starship
import com.transferwise.gustavobonilla.swapi.model.Vehicle
import io.reactivex.Observable

interface Item {
  interface Repository {
    /**
     * Gets the [Root] object [Observable] from the data source available.
     */
    fun getRoot(): Observable<Root>

    /**
     * Gets the [Film] list object [Observable] from the data source available.
     */
    fun getAllFilms(page: Int): Observable<List<Film>>

    /**
     * Gets the [People] list object [Observable] from the data source available.
     */
    fun getAllPeople(page: Int): Observable<List<People>>

    /**
     * Gets the [Planet] list object [Observable] from the data source available.
     */
    fun getAllPlanet(page: Int): Observable<List<Planet>>

    /**
     * Gets the [Species] list object [Observable] from the data source available.
     */
    fun getAllSpecies(page: Int): Observable<List<Species>>

    /**
     * Gets the [Starship] list object [Observable] from the data source available.
     */
    fun getAllStarShip(page: Int): Observable<List<Starship>>

    /**
     * Gets the [Vehicle] list object [Observable] from the data source available.
     */
    fun getAllVehicle(page: Int): Observable<List<Vehicle>>

    /**
     * Gets the [Film] object [Observable] from the data source available.
     */
    fun getFilm(id: Int, url: String): Observable<Film>

    /**
     * Gets the [People] object [Observable] from the data source available.
     */
    fun getPeople(id: Int, url: String): Observable<People>

    /**
     * Gets the [Starship] object [Observable] from the data source available.
     */
    fun getStarShip(id: Int, url: String): Observable<Starship>

    /**
     * Gets the [Vehicle] object [Observable] from the data source available.
     */
    fun getVehicle(id: Int, url: String): Observable<Vehicle>

    /**
     * Gets the [Species] object [Observable] from the data source available.
     */
    fun getSpecies(id: Int, url: String): Observable<Species>

    /**
     * Gets the [Planet] object [Observable] from the data source available.
     */
    fun getPlanet(id: Int, url: String): Observable<Planet>
  }
}