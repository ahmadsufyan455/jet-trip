package com.zerodev.jettrip.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zerodev.jettrip.R
import com.zerodev.jettrip.di.Injection
import com.zerodev.jettrip.ui.ViewModelFactory
import com.zerodev.jettrip.ui.common.UiState
import com.zerodev.jettrip.ui.components.ImageCoil
import com.zerodev.jettrip.ui.theme.JetTripTheme

@Composable
fun DetailScreen(
    id: Long,
    viewModel: DetailScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> viewModel.getTripById(id)
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    id = data.id,
                    imageUrl = data.image,
                    name = data.name,
                    location = data.location,
                    shortDesc = data.shortDesc,
                    longDesc = data.longDesc,
                    isFavorite = data.isFavorite,
                    onBackClick = navigateBack,
                    onFavoriteButtonClick = { id, isFavorite ->
                        viewModel.addToFavorite(id, isFavorite)
                    }
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    id: Long,
    imageUrl: String,
    name: String,
    location: String,
    shortDesc: String,
    longDesc: String,
    isFavorite: Boolean,
    onBackClick: () -> Unit,
    onFavoriteButtonClick: (id: Long, isFavorite: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Box {
            ImageCoil(
                imageUrl = imageUrl,
                name = name,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .shadow(elevation = 4.dp)
                        .padding(16.dp)
                        .clickable { onBackClick() },
                )
                Icon(
                    imageVector = if (!isFavorite) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    modifier = Modifier
                        .shadow(elevation = 4.dp)
                        .padding(16.dp)
                        .clickable { onFavoriteButtonClick(id, isFavorite) }
                )
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Row() {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.h3,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = location,
                        style = MaterialTheme.typography.body1,
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = shortDesc,
                    style = MaterialTheme.typography.body1,
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.overview),
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = longDesc,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    JetTripTheme {
        DetailContent(
            id = 1,
            imageUrl = "https://raw.githubusercontent.com/ahmadsufyan455/lomboknesia/main/poster/pelawangan.jpg",
            name = "Pelawangan Sembalun",
            location = "East Lombok",
            shortDesc = "Hiking, lake, mountain and camping",
            longDesc = "Plawangan Sembalun is the entrance to the top of Rinjani. This place is claimed to be one of the best spots for Mount Rinjani. The beauty of being here is beyond words. You can only be stunned and amazed.",
            isFavorite = false,
            onBackClick = { },
            onFavoriteButtonClick = { _, _ -> },
        )
    }
}