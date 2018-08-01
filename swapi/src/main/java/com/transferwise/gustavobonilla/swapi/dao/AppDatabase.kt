/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * AppDatabase.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.swapi.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.transferwise.gustavobonilla.swapi.model.Film
import com.transferwise.gustavobonilla.swapi.model.People
import com.transferwise.gustavobonilla.swapi.model.Planet
import com.transferwise.gustavobonilla.swapi.model.Root
import com.transferwise.gustavobonilla.swapi.model.Species
import com.transferwise.gustavobonilla.swapi.model.Starship
import com.transferwise.gustavobonilla.swapi.model.Vehicle

@Database(entities = [Root::class, Film::class, People::class, Planet::class, Species::class, Starship::class, Vehicle::class], version = AppDatabase.VERSION, exportSchema = false)
@TypeConverters(StarwarsTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
  companion object {
    const val VERSION = 1
  }

  /**
   * Gets the [RootDao] for the [Root] model.
   */
  abstract fun rootDao(): RootDao

  /**
   * Gets the [FilmDao] for the [Film] model.
   */
  abstract fun filmDao(): FilmDao

  /**
   * Gets the [PeopleDao] for the [People] model.
   */
  abstract fun peopleDao(): PeopleDao

  /**
   * Gets the [PlanetDao] for the [Planet] model.
   */
  abstract fun planetDao(): PlanetDao

  /**
   * Gets the [SpeciesDao] for the [Species] model.
   */
  abstract fun speciesDao(): SpeciesDao

  /**
   * Gets the [StarShipDao] for the [Starship] model.
   */
  abstract fun starshipDao(): StarShipDao

  /**
   * Gets the [VehicleDao] for the [Vehicle] model.
   */
  abstract fun vehicleDao(): VehicleDao
}