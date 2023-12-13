package com.dicoding.submissionakhiraplikasiandroidjetpack.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.submissionakhiraplikasiandroidjetpack.di.Injection
import com.dicoding.submissionakhiraplikasiandroidjetpack.model.OrderFruit
import com.dicoding.submissionakhiraplikasiandroidjetpack.ui.common.UiState
import com.dicoding.submissionakhiraplikasiandroidjetpack.ui.component.FruitItem
import com.dicoding.submissionakhiraplikasiandroidjetpack.ui.theme.ViewModelFactory

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllFruits()
            }
            is UiState.Success -> {
                HomeContent(
                    orderFruit = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    orderFruit: List<OrderFruit>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(110.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(orderFruit) { data ->
            FruitItem(
                image = data.fruit.image,
                title = data.fruit.title,
                requiredMoney = data.fruit.requiredMoney,
                modifier = Modifier.clickable {
                    navigateToDetail(data.fruit.id)
                }
            )
        }
    }
}