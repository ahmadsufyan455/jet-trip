package com.zerodev.jettrip.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.zerodev.jettrip.R

@Composable
fun ImageCoil(imageUrl: String, name: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.pelawangan),
        contentDescription = name,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}