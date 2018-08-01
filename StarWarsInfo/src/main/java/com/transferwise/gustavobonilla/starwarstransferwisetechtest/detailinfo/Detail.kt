/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * Detail.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.detailinfo

import com.transferwise.gustavobonilla.starwarstransferwisetechtest.Lifecycle
import com.transferwise.gustavobonilla.swapi.model.Film
import com.transferwise.gustavobonilla.swapi.model.People
import com.transferwise.gustavobonilla.swapi.model.Planet
import com.transferwise.gustavobonilla.swapi.model.Species
import com.transferwise.gustavobonilla.swapi.model.Starship
import com.transferwise.gustavobonilla.swapi.model.Vehicle

interface Detail {
  interface View {
    /**
     * Initialize the view.
     */
    fun init()

    /**
     * Populates the info of a [Film] in the view.
     *
     * @param film the [Film] object that contains the info to be populated
     */
    fun populateFilmInfo(film: Film)

    /**
     * Populates the info of a [People] in the view.
     *
     * @param people the [People] object that contains the info to be populated
     */
    fun populatePeopleInfo(people: People)

    /**
     * Populates the info of a [Starship] in the view.
     *
     * @param starship the [Starship] object that contains the info to be populated
     */
    fun populateStarshipInfo(starship: Starship)

    /**
     * Populates the info of a [Vehicle] in the view.
     *
     * @param vehicle the [Vehicle] object that contains the info to be populated
     */
    fun populateVehicleInfo(vehicle: Vehicle)

    /**
     * Populates the info of a [Species] in the view.
     *
     * @param species the [Species] object that contains the info to be populated
     */
    fun populateSpeciesInfo(species: Species)

    /**
     * Populates the info of a [Planet] in the view.
     *
     * @param planet the [Planet] object that contains the info to be populated
     */
    fun populatePlanetInfo(planet: Planet)

    /**
     * Shows error message when necessary.
     */
    fun showError()

    /**
     * Hide error message when necessary.
     */
    fun hideError()
  }

  interface Presenter: Lifecycle.Callback {
    /**
     * Initialize the presenter.
     */
    fun initialize()
  }
}