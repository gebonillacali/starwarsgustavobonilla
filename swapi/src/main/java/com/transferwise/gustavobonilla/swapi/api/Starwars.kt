/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * Starwars.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.swapi.api

import com.transferwise.gustavobonilla.swapi.model.Film
import com.transferwise.gustavobonilla.swapi.model.People
import com.transferwise.gustavobonilla.swapi.model.Planet
import com.transferwise.gustavobonilla.swapi.model.Root
import com.transferwise.gustavobonilla.swapi.model.Species
import com.transferwise.gustavobonilla.swapi.model.StarWarsModelList
import com.transferwise.gustavobonilla.swapi.model.Starship
import com.transferwise.gustavobonilla.swapi.model.Vehicle
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Defines endpoint resources for the swapi.co (Starwars API) @see https://swapi.co/documentation
 */
interface StarWars {

  @GET("/api")
  fun getRootUrls(): Single<Root>

  @GET("/api/people")
  fun getAllPeople(@Query("page") page: Int): Single<StarWarsModelList<People>>

  @GET("/api/people/{id}")
  fun getPeople(@Path("id") peopleId: Int): Single<People>

  @GET("/api/films/")
  fun getAllFilms(@Query("page") page: Int): Single<StarWarsModelList<Film>>

  @GET("/api/films/{id}/")
  fun getFilm(@Path("id") filmId: Int): Single<Film>

  @GET("/api/starships")
  fun getAllStarships(@Query("page") page: Int): Single<StarWarsModelList<Starship>>

  @GET("/api/starships/{id}")
  fun getStarship(@Path("id") starshipId: Int): Single<Starship>

  @GET("/api/vehicles")
  fun getAllVehicles(@Query("page") page: Int): Single<StarWarsModelList<Vehicle>>

  @GET("/api/vehicles/{id}")
  fun getVehicle(@Path("id") vehicleId: Int): Single<Vehicle>

  @GET("/api/species")
  fun getAllSpecies(@Query("page") page: Int): Single<StarWarsModelList<Species>>

  @GET("/api/species/{id}")
  fun getSpecies(@Path("id") speciesId: Int): Single<Species>

  @GET("/api/planets")
  fun getAllPlanets(@Query("page") page: Int): Single<StarWarsModelList<Planet>>

  @GET("/api/planets/{id}")
  fun getPlanet(@Path("id") planetId: Int): Single<Planet>
}
