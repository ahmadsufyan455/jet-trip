package com.zerodev.jettrip.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zerodev.jettrip.R
import com.zerodev.jettrip.ui.theme.JetTripTheme

@Composable
fun EmptyPage(
    modifier: Modifier = Modifier,
    emptyMessage: String
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.empty),
                contentDescription = null,
                modifier = Modifier
                    .size(250.dp)
                    .padding(16.dp)
            )
            Text(
                text = emptyMessage,
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyPagePreview() {
    JetTripTheme {
        EmptyPage(emptyMessage = "Empty Favorite")
    }
}