/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * DetailFragment.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.detailinfo.view


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.R
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.StarWarsApplication
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.detailinfo.Detail
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.detailinfo.di.DetailModule
import com.transferwise.gustavobonilla.swapi.model.Film
import com.transferwise.gustavobonilla.swapi.model.People
import com.transferwise.gustavobonilla.swapi.model.Planet
import com.transferwise.gustavobonilla.swapi.model.Species
import com.transferwise.gustavobonilla.swapi.model.Starship
import com.transferwise.gustavobonilla.swapi.model.Vehicle
import kotlinx.android.synthetic.main.fragment_detail.background
import kotlinx.android.synthetic.main.fragment_detail.detailButtonAction
import kotlinx.android.synthetic.main.fragment_detail.detailFifthText
import kotlinx.android.synthetic.main.fragment_detail.detailFirstText
import kotlinx.android.synthetic.main.fragment_detail.detailFourthText
import kotlinx.android.synthetic.main.fragment_detail.detailImage
import kotlinx.android.synthetic.main.fragment_detail.detailSecondText
import kotlinx.android.synthetic.main.fragment_detail.detailThirdText
import kotlinx.android.synthetic.main.fragment_detail.detailTitle
import kotlinx.android.synthetic.main.fragment_detail.errorView
import kotlinx.android.synthetic.main.fragment_detail.scrollView
import javax.inject.Inject
import com.bumptech.glide.request.RequestOptions



class DetailFragment: Fragment(), Detail.View {

  companion object {
    private const val KEY_ID = "ID"
    private const val KEY_URL = "URL"


    @JvmStatic
    fun newInstance(id: String, url: String) =
        DetailFragment().apply {
          arguments = Bundle().apply {
            putString(KEY_ID, id)
            putString(KEY_URL, url)
          }
        }
  }

  @Inject
  lateinit var presenter: Detail.Presenter

  //region Fragment Lifecycle
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      val id = it.getString(KEY_ID)
      val url = it.getString(KEY_URL)
      inject(id, url)
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_detail, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    init()
  }

  override fun onResume() {
    super.onResume()
    background.registerSensorManager()
  }

  override fun onPause() {
    background.unregisterSensorManager()
    super.onPause()
  }

  override fun onDestroy() {
    presenter.onDestroy()
    super.onDestroy()
  }
  //endregion

  //region Detail.View
  override fun init() {
    presenter.initialize()
  }

  override fun populateFilmInfo(film: Film) {
    detailTitle.text = film.title
    detailFirstText.text = "Episode: ${film.episodeId}"
    detailSecondText.text = "Release date: ${film.releaseDate}"
    detailThirdText.text = "Director: ${film.director}"
    detailFourthText.text = "Producer: ${film.producer}"
    detailFifthText.text = film.openingCrawl

    val requestOptions = RequestOptions()
    requestOptions.placeholder(R.drawable.film)
    requestOptions.error(R.drawable.film)

    Glide.with(view!!)
        .setDefaultRequestOptions(requestOptions)
        .load(film.getUrlHeroImageFilm())
        .into(detailImage)

    detailButtonAction.setOnClickListener {
      showOpeningCrawlInBrowser(film.episodeId)
    }
  }

  override fun populatePeopleInfo(people: People) {
    detailTitle.text = people.name
    detailFirstText.text = "Gender: ${people.gender}"
    detailSecondText.text = "Hair color: ${people.hairColor}"
    detailThirdText.text = "Skin color: ${people.skinColor}"
    detailFourthText.text = "Birth year: ${people.birthYear}"
    detailFifthText.text = "Height: ${people.height}"
    detailImage.setImageResource(R.drawable.people)
    detailButtonAction.visibility = View.GONE
  }

  override fun populateStarshipInfo(starship: Starship) {
    detailTitle.text = starship.name
    detailFirstText.text = "Model: ${starship.model}"
    detailSecondText.text = "Manufacturer: ${starship.manufacturer}"
    detailThirdText.text = "Starship class: ${starship.starshipClass}"
    detailFourthText.text = "Length: ${starship.length}"
    detailFifthText.text = "Cost in credits: ${starship.costInCredits}"
    detailImage.setImageResource(R.drawable.starship)
    detailButtonAction.visibility = View.GONE
  }

  override fun populateVehicleInfo(vehicle: Vehicle) {
    detailTitle.text = vehicle.name
    detailFirstText.text = "Model: ${vehicle.model}"
    detailSecondText.text = "Manufacturer: ${vehicle.manufacturer}"
    detailThirdText.text = "Vehicle class: ${vehicle.vehicleClass}"
    detailFourthText.text = "Length: ${vehicle.length}"
    detailFifthText.text = "Cost in credits: ${vehicle.costInCredits}"
    detailImage.setImageResource(R.drawable.vehicles)
    detailButtonAction.visibility = View.GONE
  }

  override fun populateSpeciesInfo(species: Species) {
    detailTitle.text = species.name
    detailFirstText.text = "Language: ${species.language}"
    detailSecondText.text = "Hair color: ${species.hairColors}"
    detailThirdText.text = "Designation: ${species.designation}"
    detailFourthText.text = "Classification: ${species.classification}"
    detailFifthText.text = "Avg Height: ${species.averageHeight}"
    detailImage.setImageResource(R.drawable.species)
    detailButtonAction.visibility = View.GONE
  }

  override fun populatePlanetInfo(planet: Planet) {
    detailTitle.text = planet.name
    detailFirstText.text = "Climate: ${planet.climate}"
    detailSecondText.text = "Diameter: ${planet.diameter}"
    detailThirdText.text = "Gravity: ${planet.gravity}"
    detailFourthText.text = "Terrain: ${planet.terrain}"
    detailFifthText.text = "Population: ${planet.population}"
    detailImage.setImageResource(R.drawable.planet)
    detailButtonAction.visibility = View.GONE
  }

  override fun showError() {
    scrollView.visibility = View.GONE
    errorView.visibility = View.VISIBLE
  }

  override fun hideError() {
    scrollView.visibility = View.VISIBLE
    errorView.visibility = View.GONE
  }
  //endregion

  //region private
  /**
   * Opens the Opening Crawl of a Film in a external browser.
   *
   * @param episodeNumber the episode number for a Film.
   */
  private fun showOpeningCrawlInBrowser(episodeNumber: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://starwarsintrocreator.kassellabs.io/#!/Episode$episodeNumber"))
    startActivity(browserIntent)
  }

  /**
   * Injects the dependencies in the fragment.
   */
  private fun inject(id: String, url: String) {
    (activity?.application as StarWarsApplication)
        .starWarsApplicationComponent
        .plus(DetailModule(this, id, url))
        .inject(this)
  }
  //endregion
}
