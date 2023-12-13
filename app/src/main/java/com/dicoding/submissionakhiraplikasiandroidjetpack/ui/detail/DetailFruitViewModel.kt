package com.dicoding.submissionakhiraplikasiandroidjetpack.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.submissionakhiraplikasiandroidjetpack.data.FruitRepository
import com.dicoding.submissionakhiraplikasiandroidjetpack.model.Fruit
import com.dicoding.submissionakhiraplikasiandroidjetpack.model.OrderFruit
import com.dicoding.submissionakhiraplikasiandroidjetpack.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailFruitViewModel(
    private val repository: FruitRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderFruit>> =
        MutableStateFlow(UiState.Loading)
    val uiState: MutableStateFlow<UiState<OrderFruit>>
        get() = _uiState

    fun getFruitById(fruitId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderFruitById(fruitId))
        }
    }

    fun addToCart(fruit: Fruit, count: Int) {
        viewModelScope.launch {
            repository.updateOrderFruit(fruit.id, count)
        }
    }
}