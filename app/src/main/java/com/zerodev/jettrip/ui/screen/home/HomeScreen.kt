package com.zerodev.jettrip.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zerodev.jettrip.R
import com.zerodev.jettrip.di.Injection
import com.zerodev.jettrip.ui.ViewModelFactory
import com.zerodev.jettrip.ui.common.UiState
import com.zerodev.jettrip.ui.components.HeadingBar
import com.zerodev.jettrip.ui.components.SearchBar
import com.zerodev.jettrip.ui.components.TripContent

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit
) {
    val query by viewModel.query
    Column {
        HeadingBar()
        SearchBar(
            query = query,
            onQueryChange = viewModel::search
        )
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> viewModel.getTrips()
                is UiState.Success -> {
                    TripContent(
                        trips = uiState.data,
                        emptyMessage = stringResource(id = R.string.empty_data),
                        navigateToDetail = navigateToDetail
                    )
                }
                is UiState.Error -> {}
            }
        }
    }
}