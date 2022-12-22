package com.zerodev.jettrip.ui.screen.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.zerodev.jettrip.R
import com.zerodev.jettrip.di.Injection
import com.zerodev.jettrip.ui.ViewModelFactory
import com.zerodev.jettrip.ui.common.UiState
import com.zerodev.jettrip.ui.components.TripContent

@Composable
fun FavoriteScreen(
    viewModel: FavoriteScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> viewModel.getFavoriteTrip()
            is UiState.Success -> {
                TripContent(
                    trips = uiState.data,
                    emptyMessage = stringResource(id = R.string.empty_fav),
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {}
        }
    }
}