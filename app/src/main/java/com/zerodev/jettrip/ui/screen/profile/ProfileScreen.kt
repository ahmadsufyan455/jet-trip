package com.zerodev.jettrip.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zerodev.jettrip.R
import com.zerodev.jettrip.ui.theme.JetTripTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.fyn),
                contentDescription = stringResource(id = R.string.author),
                modifier = Modifier
                    .size(180.dp)
                    .padding(16.dp)
                    .clip(shape = CircleShape)
            )
            Text(
                text = stringResource(id = R.string.author),
                style = MaterialTheme.typography.h2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = R.string.email),
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.quote),
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    JetTripTheme {
        ProfileScreen()
    }
}