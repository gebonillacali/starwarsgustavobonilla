/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * StarWarsModelList.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.swapi.model

data class StarWarsModelList<T>(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<T>
)