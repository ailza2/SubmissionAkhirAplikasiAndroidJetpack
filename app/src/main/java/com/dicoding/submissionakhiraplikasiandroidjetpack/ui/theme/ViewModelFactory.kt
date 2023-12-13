package com.dicoding.submissionakhiraplikasiandroidjetpack.ui.theme

import androidx.lifecycle.ViewModelProvider
import com.dicoding.submissionakhiraplikasiandroidjetpack.data.FruitRepository
import com.dicoding.submissionakhiraplikasiandroidjetpack.ui.cart.CartViewModel
import com.dicoding.submissionakhiraplikasiandroidjetpack.ui.detail.DetailFruitViewModel
import com.dicoding.submissionakhiraplikasiandroidjetpack.ui.home.HomeViewModel


class ViewModelFactory(private val repository: FruitRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailFruitViewModel::class.java)) {
            return DetailFruitViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}