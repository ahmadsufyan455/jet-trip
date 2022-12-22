package com.zerodev.jettrip.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zerodev.jettrip.R
import com.zerodev.jettrip.ui.theme.JetTripTheme

@Composable
fun HeadingBar(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.welcome),
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            text = stringResource(id = R.string.destination),
            style = MaterialTheme.typography.body1,
            color = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeadingBarPreview() {
    JetTripTheme {
        HeadingBar()
    }
}