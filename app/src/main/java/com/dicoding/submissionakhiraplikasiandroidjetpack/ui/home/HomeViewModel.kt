package com.dicoding.submissionakhiraplikasiandroidjetpack.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.submissionakhiraplikasiandroidjetpack.data.FruitRepository
import com.dicoding.submissionakhiraplikasiandroidjetpack.model.OrderFruit
import com.dicoding.submissionakhiraplikasiandroidjetpack.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: FruitRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderFruit>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: MutableStateFlow<UiState<List<OrderFruit>>>
        get() = _uiState

    fun getAllFruits() {
        viewModelScope.launch {
            repository.getAllFruit()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderfruits ->
                    _uiState.value = UiState.Success(orderfruits)
                }
        }
    }
}