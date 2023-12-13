package com.dicoding.submissionakhiraplikasiandroidjetpack.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.submissionakhiraplikasiandroidjetpack.data.FruitRepository
import com.dicoding.submissionakhiraplikasiandroidjetpack.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: FruitRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddedOrderFruit() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderFruit()
                .collect { orderFruit ->
                    val totalRequiredPoint =
                        orderFruit.sumOf { it.fruit.requiredMoney * it.count }
                    _uiState.value = UiState.Success(CartState(orderFruit, totalRequiredPoint))
                }
        }
    }

    fun updateOrderFruit(fruitId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderFruit(fruitId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderFruit()
                    }
                }
        }
    }
}