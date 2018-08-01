/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * ItemAdapter.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.R
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.adapteritem.FilmAdapterItem
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.adapteritem.PeopleAdapterItem
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.adapteritem.PlanetAdapterItem
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.adapteritem.RootAdapterItem
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.adapteritem.SpeciesAdapterItem
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.adapteritem.StarshipAdapterItem
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.adapteritem.VehicleAdapterItem
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.viewholder.SectionItemViewHolder
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter.viewholder.RootViewHolder
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.inflate
import com.transferwise.gustavobonilla.swapi.model.Film
import com.transferwise.gustavobonilla.swapi.model.People
import com.transferwise.gustavobonilla.swapi.model.Planet
import com.transferwise.gustavobonilla.swapi.model.RootItem
import com.transferwise.gustavobonilla.swapi.model.Species
import com.transferwise.gustavobonilla.swapi.model.StarWarsModel
import com.transferwise.gustavobonilla.swapi.model.Starship
import com.transferwise.gustavobonilla.swapi.model.Vehicle

class ItemAdapter(private val clickListener: ClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  private val list: MutableList<AdapterItem> = mutableListOf()
  private val viewHolderFactory: MutableMap<Int, CreateViewHolder> = mutableMapOf(
      R.layout.root_view to { layout, parent -> RootViewHolder(parent.inflate(layout))},
      R.layout.section_item_view to { layout, parent -> SectionItemViewHolder(parent.inflate(layout))}
  )

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return viewHolderFactory[viewType]!!.invoke(viewType, parent)
  }

  override fun getItemCount(): Int = list.size

  override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
    list[position].bindData(viewHolder)
  }

  override fun getItemViewType(position: Int): Int {
    return list[position].layoutRes
  }

  /**
   * Adds the items into the list for the adapter.
   *
   * @param items the items to be added to the list for the adapter.
   */
  fun addItems(items: List<StarWarsModel>) {
    synchronized(list, {
      val adapterItems = items.map {
        when (it) {
          is RootItem -> RootAdapterItem(it, clickListener)
          is Film -> FilmAdapterItem(it, clickListener)
          is People -> PeopleAdapterItem(it, clickListener)
          is Planet -> PlanetAdapterItem(it, clickListener)
          is Species -> SpeciesAdapterItem(it, clickListener)
          is Starship -> StarshipAdapterItem(it, clickListener)
          is Vehicle -> VehicleAdapterItem(it, clickListener)
          else -> null
        }
      }.filter {
        it != null
      }.map { it as AdapterItem }

      if (list.containsAll(adapterItems)) {
        return
      }

      val positionStart = if (list.isNotEmpty()) list.size - 1 else 0
      list.addAll(adapterItems)
      notifyItemRangeInserted(positionStart, adapterItems.size - 1)
    })
  }
}