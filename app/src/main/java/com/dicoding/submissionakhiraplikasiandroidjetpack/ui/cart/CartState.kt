package com.dicoding.submissionakhiraplikasiandroidjetpack.ui.cart

import com.dicoding.submissionakhiraplikasiandroidjetpack.model.OrderFruit

data class CartState(
    val orderFruit: List<OrderFruit>,
    val totalRequiredMoney: Int
)