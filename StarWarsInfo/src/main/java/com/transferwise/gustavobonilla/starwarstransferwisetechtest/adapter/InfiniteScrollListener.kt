/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * InfiniteScrollListener.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE

class InfiniteScrollListener(threshold: Int = 1,
    private var lazyLoader: LazyLoader = LazyLoader(threshold),
    val onLoadingMore: () -> Unit,
    val onStateIdle: () -> Unit = {}): RecyclerView.OnScrollListener() {

  override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
    if (dy > 0 || dx > 0) {
      val layoutManager = recyclerView.layoutManager as? LinearLayoutManager
      lazyLoader.loadMoreIfNeeded(layoutManager, onLoadingMore)
    }
  }

  override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
    super.onScrollStateChanged(recyclerView, newState)
    if (newState == SCROLL_STATE_IDLE) {
      onStateIdle()
    }
  }

  /**
   * Rests the lazy loader state.
   */
  fun reset() {
    lazyLoader.reset()
  }
}

class LazyLoader(val threshold: Int = 1) {
  private var loading: Boolean = false
  private var previousItemCount: Int = 0

  fun loadMoreIfNeeded(layoutManager: LinearLayoutManager?, onLoadingMore: () -> Unit) {
    layoutManager?.run {
      if (loading && (itemCount > previousItemCount)) {
        loading = false
        previousItemCount = itemCount
      }

      val lastVisibleItem = childCount + findFirstVisibleItemPosition()
      val indexToTriggerLoading = itemCount - threshold

      if (!loading && (lastVisibleItem >= indexToTriggerLoading)) {
        onLoadingMore()
        loading = true
      }
    }
  }

  /**
   * Resets that the loader is loading and the previous item count to 0.
   */
  fun reset() {
    previousItemCount = 0
    loading = false
  }
}