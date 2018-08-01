/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * Lifecycle.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest

interface Lifecycle {

  interface Callback {
    /**
     * Represent the onResume event of Lifecycle.
     */
    fun onResume() {}

    /**
     * Represent the onPause event of Lifecycle.
     */
    fun onPause() {}

    /**
     * Represent the onDestroy event of Lifecycle.
     */
    fun onDestroy() {}
  }
}