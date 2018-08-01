/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * ItemRepository.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.swapi.repository

import android.util.Log
import com.transferwise.gustavobonilla.swapi.api.StarWars
import com.transferwise.gustavobonilla.swapi.dao.AppDatabase
import com.transferwise.gustavobonilla.swapi.model.Film
import com.transferwise.gustavobonilla.swapi.model.People
import com.transferwise.gustavobonilla.swapi.model.Planet
import com.transferwise.gustavobonilla.swapi.model.Root
import com.transferwise.gustavobonilla.swapi.model.Species
import com.transferwise.gustavobonilla.swapi.model.Starship
import com.transferwise.gustavobonilla.swapi.model.Vehicle
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

open class ItemRepository(
    private val api: StarWars,
    private val database: AppDatabase
): Item.Repository {

  companion object {
    private const val DEBOUNCE_TIME = 400L
  }

  //region Root
  override fun getRoot(): Observable<Root> {
    return Observable.concat(
        getRootFromDb()
            .materialize()
            .filter { !it.isOnError }
            .dematerialize<Root>()
            .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS),
        getRootFromApi()
            .materialize()
            .filter { !it.isOnError }
            .dematerialize<Root>()
            .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
    )
  }

  /**
   * Gets the [Root] object [Observable] from the swapi.co (Starwars API)
   */
  private fun getRootFromApi(): Observable<Root> {
    return api.getRootUrls()
        .toObservable()
        .doOnNext {
          storeRootInDb(it)
        }
  }

  /**
   * Gets the [Root] object [Observable] from SQLite database.
   */
  private fun getRootFromDb(): Observable<Root> {
    return database.rootDao()
        .get()
        .toObservable()
  }

  /**
   * Stores the [Root] object into the SQLite database.
   *
   * @param root the [Root] object to be stored.
   */
  private fun storeRootInDb(root: Root) {
    Observable.fromCallable { database.rootDao().insert(root) }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe {
          Log.d("Itemrepository", "Done!")
        }
  }
  //endregion

  //region Films
  override fun getAllFilms(page: Int): Observable<List<Film>> {
    return Observable.concatArray(
        getAllFilmsFromDb(),
        getAllFilmsFromApi(page)
            .materialize()
            .filter { !it.isOnError }
            .dematerialize<List<Film>>()
            .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
    )
  }

  override fun getFilm(id: Int, url: String): Observable<Film> {
    return Observable.concatArray(
        getFilmFromDb(url)
            .materialize()
            .filter { !it.isOnError }
            .dematerialize<Film>()
            .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS),
        getFilmFromApi(id)
            .materialize()
            .filter { !it.isOnError }
            .dematerialize<Film>()
            .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
    )
  }

  /**
   * Gets the [Film] list object [Observable] from SQLite database.
   */
  private fun getAllFilmsFromDb(): Observable<List<Film>> {
    return database.filmDao()
        .getAll()
        .toObservable()
  }

  /**
   * Gets the [Film] list object [Observable] from the swapi.co (Starwars API)
   */
  private fun getAllFilmsFromApi(page: Int): Observable<out List<Film>> {
    return api.getAllFilms(page)
        .map { it.results }
        .toObservable()
        .doOnNext {
          storeFilmsInDb(it)
        }
  }

  /**
   * Gets the [Film] object [Observable] from SQLite database.
   */
  private fun getFilmFromDb(url: String): Observable<Film> {
    return database.filmDao().get(url)
        .toObservable()
  }

  /**
   * Gets the [Film] object [Observable] from the swapi.co (Starwars API)
   */
  private fun getFilmFromApi(id: Int): Observable<Film> {
    return api.getFilm(id)
        .toObservable()
        .doOnNext {
          storeSingleRecordInDb(it)
        }
  }

  /**
   * Stores the [Film] list object into the SQLite database.
   *
   * @param films the [Film] list object to be stored.
   */
  private fun storeFilmsInDb(films: List<Film>) {
    Observable.fromCallable { database.filmDao().insertAll(films) }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe {
          Log.d("Itemrepository", "Done!")
        }
  }

  /**
   * Stores the [Film] object into the SQLite database.
   *
   * @param film the [Film] object to be stored.
   */
  private fun storeSingleRecordInDb(film: Film) {
    Observable.fromCallable { database.filmDao().insert(film) }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe {
          Log.d("Itemrepository", "Done!")
        }
  }
  //endregion

  //region People
  override fun getAllPeople(page: Int): Observable<List<People>> {
    return Observable.concatArray(
        getAllPeopleFromDb(),
        getAllPeopleFromApi(page)
            .materialize()
            .filter { !it.isOnError }
            .dematerialize<List<People>>()
            .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
    )
  }

  override fun getPeople(id: Int, url: String): Observable<People> {
    return Observable.concatArray(
        getPeopleFromDb(url)
            .materialize()
            .filter { !it.isOnError }
            .dematerialize<People>()
            .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS),
        getPeopleFromApi(id)
            .materialize()
            .filter { !it.isOnError }
            .dematerialize<People>()
            .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
    )
  }

  /**
   * Gets the [People] list object [Observable] from SQLite database.
   */
  private fun getAllPeopleFromDb(): Observable<List<People>> {
    return database.peopleDao()
        .getAll()
        .toObservable()
  }

  /**
   * Gets the [People] list object [Observable] from the swapi.co (Starwars API)
   */
  private fun getAllPeopleFromApi(page: Int): Observable<out List<People>> {
    return api.getAllPeople(page)
        .map { it.results }
        .toObservable()
        .doOnNext {
          storePeopleInDb(it)
        }
  }

  /**
   * Stores the [People] list object into the SQLite database.
   *
   * @param people the [People] list object to be stored.
   */
  private fun storePeopleInDb(people: List<People>) {
    Observable.fromCallable { database.peopleDao().insertAll(people) }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe {
          Log.d("Itemrepository", "Done!")
        }
  }

  /**
   * Gets the [People] object [Observable] from the swapi.co (Starwars API)
   */
  private fun getPeopleFromApi(id: Int): Observable<People> {
    return api.getPeople(id)
        .toObservable()
        .doOnNext {
          storeSinglePeopleRecordInDb(it)
        }
  }

  /**
   * Gets the [People] object [Observable] from SQLite database.
   */
  private fun getPeopleFromDb(url: String): Observable<People> {
    return database.peopleDao().get(url)
        .toObservable()
  }

  /**
   * Stores the [People] object into the SQLite database.
   *
   * @param people the [People] object to be stored.
   */
  private fun storeSinglePeopleRecordInDb(people: People) {
    Observable.fromCallable { database.peopleDao().insert(people) }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe {
          Log.d("Itemrepository", "Done!")
        }
  }
  //endregion

  //region Planet
  override fun getAllPlanet(page: Int): Observable<List<Planet>> {
    return Observable.concatArray(
        getAllPlanetFromDb(),
        getAllPlanetFromApi(page)
            .materialize()
            .filter { !it.isOnError }
            .dematerialize<List<Planet>>()
            .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
    )
  }

  override fun getPlanet(id: Int, url: String): Observable<Planet> {
    return Observable.concatArray(
        getPlanetFromDb(url)
            .materialize()
            .filter { !it.isOnError }
            .dematerialize<Planet>()
            .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS),
        getPlanetFromApi(id)
            .materialize()
            .filter { !it.isOnError }
            .dematerialize<Planet>()
            .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
    )
  }

  /**
   * Gets the [Planet] list object [Observable] from SQLite database.
   */
  private fun getAllPlanetFromDb(): Observable<List<Planet>> {
    return database.planetDao()
        .getAll()
        .toObservable()
  }

  /**
   * Gets the [Planet] list object [Observable] from the swapi.co (Starwars API)
   */
  private fun getAllPlanetFromApi(page: Int): Observable<out List<Planet>> {
    return api.getAllPlanets(page)
        .map { it.results }
        .toObservable()
        .doOnNext {
          storePlanetInDb(it)
        }
  }

  /**
   * Stores the [Planet] list object into the SQLite database.
   *
   * @param planet the [Planet] list object to be stored.
   */
  private fun storePlanetInDb(planet: List<Planet>) {
    Observable.fromCallable { database.planetDao().insertAll(planet) }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe {
          Log.d("Itemrepository", "Done!")
        }
  }

  /**
   * Gets the [Planet] object [Observable] from the swapi.co (Starwars API)
   */
  private fun getPlanetFromApi(id: Int): Observable<Planet> {
    return api.getPlanet(id)
        .toObservable()
        .doOnNext {
          storeSinglePlanetRecordInDb(it)
        }
  }

  /**
   * Gets the [Planet] object [Observable] from SQLite database.
   */
  private fun getPlanetFromDb(url: String): Observable<Planet> {
    return database.planetDao().get(url)
        .toObservable()
  }

  /**
   * Stores the [Planet] object into the SQLite database.
   *
   * @param planet the [Planet] object to be stored.
   */
  private fun storeSinglePlanetRecordInDb(planet: Planet) {
    Observable.fromCallable { database.planetDao().insert(planet) }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe {
          Log.d("Itemrepository", "Done!")
        }
  }
  //endregion

  //region Species
  override fun getAllSpecies(page: Int): Observable<List<Species>> {
    return Observable.concatArray(
        getAllSpeciesFromDb(),
        getAllSpeciesFromApi(page)
            .materialize()
            .filter { !it.isOnError }
            .dematerialize<List<Species>>()
            .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
    )
  }

  override fun getSpecies(id: Int, url: String): Observable<Species> {
    return Observable.concatArray(
        getSpeciesFromDb(url)
            .materialize()
            .filter { !it.isOnError }
            .dematerialize<Species>()
            .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS),
        getSpeciesFromApi(id)
            .materialize()
            .filter { !it.isOnError }
            .dematerialize<Species>()
            .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
    )
  }

  /**
   * Gets the [Species] list object [Observable] from SQLite database.
   */
  private fun getAllSpeciesFromDb(): Observable<List<Species>> {
    return database.speciesDao()
        .getAll()
        .toObservable()
  }

  /**
   * Gets the [Species] list object [Observable] from the swapi.co (Starwars API)
   */
  private fun getAllSpeciesFromApi(page: Int): Observable<out List<Species>> {
    return api.getAllSpecies(page)
        .map { it.results }
        .toObservable()
        .doOnNext {
          storeSpeciesInDb(it)
        }
  }

  /**
   * Stores the [Species] list object into the SQLite database.
   *
   * @param Species the [Species] list object to be stored.
   */
  private fun storeSpeciesInDb(Species: List<Species>) {
    Observable.fromCallable { database.speciesDao().insertAll(Species) }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe {
          Log.d("Itemrepository", "Done!")
        }
  }

  /**
   * Gets the [Species] object [Observable] from the swapi.co (Starwars API)
   */
  private fun getSpeciesFromApi(id: Int): Observable<Species> {
    return api.getSpecies(id)
        .toObservable()
        .doOnNext {
          storeSingleSpeciesRecordInDb(it)
        }
  }

  /**
   * Gets the [Species] object [Observable] from SQLite database.
   */
  private fun getSpeciesFromDb(url: String): Observable<Species> {
    return database.speciesDao().get(url)
        .toObservable()
  }

  /**
   * Stores the [Species] object into the SQLite database.
   *
   * @param Species the [Species] object to be stored.
   */
  private fun storeSingleSpeciesRecordInDb(Species: Species) {
    Observable.fromCallable { database.speciesDao().insert(Species) }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe {
          Log.d("Itemrepository", "Done!")
        }
  }
  //endregion

  //region StarShip
  override fun getAllStarShip(page: Int): Observable<List<Starship>> {
    return Observable.concatArray(
        getAllStarShipFromDb(),
        getAllStarShipFromApi(page)
            .materialize()
            .filter { !it.isOnError }
            .dematerialize<List<Starship>>()
            .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
    )
  }

  override fun getStarShip(id: Int, url: String): Observable<Starship> {
    return Observable.concatArray(
        getStarShipFromDb(url)
            .materialize()
            .filter { !it.isOnError }
            .dematerialize<Starship>()
            .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS),
        getStarShipFromApi(id)
            .materialize()
            .filter { !it.isOnError }
            .dematerialize<Starship>()
            .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
    )
  }

  /**
   * Gets the [Starship] list object [Observable] from SQLite database.
   */
  private fun getAllStarShipFromDb(): Observable<List<Starship>> {
    return database.starshipDao()
        .getAll()
        .toObservable()
  }

  /**
   * Gets the [Starship] list object [Observable] from the swapi.co (Starwars API)
   */
  private fun getAllStarShipFromApi(page: Int): Observable<out List<Starship>> {
    return api.getAllStarships(page)
        .map { it.results }
        .toObservable()
        .doOnNext {
          storeStarShipInDb(it)
        }
  }

  /**
   * Stores the [StarShip] list object into the SQLite database.
   *
   * @param StarShip the [StarShip] list object to be stored.
   */
  private fun storeStarShipInDb(StarShip: List<Starship>) {
    Observable.fromCallable { database.starshipDao().insertAll(StarShip) }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe {
          Log.d("Itemrepository", "Done!")
        }
  }

  /**
   * Gets the [Starship] object [Observable] from the swapi.co (Starwars API)
   */
  private fun getStarShipFromApi(id: Int): Observable<Starship> {
    return api.getStarship(id)
        .toObservable()
        .doOnNext {
          storeSingleStarShipRecordInDb(it)
        }
  }

  /**
   * Gets the [Starship] object [Observable] from SQLite database.
   */
  private fun getStarShipFromDb(url: String): Observable<Starship> {
    return database.starshipDao().get(url)
        .toObservable()
  }

  /**
   * Stores the [StarShip] object into the SQLite database.
   *
   * @param StarShip the [StarShip] object to be stored.
   */
  private fun storeSingleStarShipRecordInDb(StarShip: Starship) {
    Observable.fromCallable { database.starshipDao().insert(StarShip) }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe {
          Log.d("Itemrepository", "Done!")
        }
  }
  //endregion

  //region Vehicle
  override fun getAllVehicle(page: Int): Observable<List<Vehicle>> {
    return Observable.concatArray(
        getAllVehicleFromDb(),
        getAllVehicleFromApi(page)
            .materialize()
            .filter { !it.isOnError }
            .dematerialize<List<Vehicle>>()
            .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
    )
  }

  override fun getVehicle(id: Int, url: String): Observable<Vehicle> {
    return Observable.concatArray(
        getVehicleFromDb(url)
            .materialize()
            .filter { !it.isOnError }
            .dematerialize<Vehicle>()
            .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS),
        getVehicleFromApi(id)
            .materialize()
            .filter { !it.isOnError }
            .dematerialize<Vehicle>()
            .debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
    )
  }

  /**
   * Gets the [Vehicle] list object [Observable] from SQLite database.
   */
  private fun getAllVehicleFromDb(): Observable<List<Vehicle>> {
    return database.vehicleDao()
        .getAll()
        .toObservable()
  }

  /**
   * Gets the [Vehicle] list object [Observable] from the swapi.co (Starwars API)
   */
  private fun getAllVehicleFromApi(page: Int): Observable<out List<Vehicle>> {
    return api.getAllVehicles(page)
        .map { it.results }
        .toObservable()
        .doOnNext {
          storeVehicleInDb(it)
        }
  }

  /**
   * Stores the [Vehicle] list object into the SQLite database.
   *
   * @param vehicles the [Vehicle] list object to be stored.
   */
  private fun storeVehicleInDb(vehicles: List<Vehicle>) {
    Observable.fromCallable { database.vehicleDao().insertAll(vehicles) }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe {
          Log.d("Itemrepository", "Done!")
        }
  }

  /**
   * Gets the [Vehicle] object [Observable] from the swapi.co (Starwars API)
   */
  private fun getVehicleFromApi(id: Int): Observable<Vehicle> {
    return api.getVehicle(id)
        .toObservable()
        .doOnNext {
          storeSingleVehicleRecordInDb(it)
        }
  }

  /**
   * Gets the [Vehicle] object [Observable] from SQLite database.
   */
  private fun getVehicleFromDb(url: String): Observable<Vehicle> {
    return database.vehicleDao().get(url)
        .toObservable()
  }

  /**
   * Stores the [Vehicle] object into the SQLite database.
   *
   * @param Vehicle the [Vehicle] object to be stored.
   */
  private fun storeSingleVehicleRecordInDb(Vehicle: Vehicle) {
    Observable.fromCallable { database.vehicleDao().insert(Vehicle) }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe {
          Log.d("Itemrepository", "Done!")
        }
  }
  //endregion
}