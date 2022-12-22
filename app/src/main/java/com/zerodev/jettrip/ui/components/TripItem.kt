package com.zerodev.jettrip.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zerodev.jettrip.ui.theme.JetTripTheme

@Composable
fun TripItem(
    imageUrl: String,
    name: String,
    location: String,
    shortDesc: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = 8.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                ImageCoil(
                    imageUrl = imageUrl,
                    name = name,
                    modifier = Modifier
                        .padding(12.dp)
                        .size(120.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            }
            Column(modifier = Modifier.padding(end = 10.dp)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.Bold,
                )
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
    }
}

@Preview(showBackground = true)
@Composable
fun TripItemPreview() {
    JetTripTheme {
        TripItem(
            "https://raw.githubusercontent.com/ahmadsufyan455/lomboknesia/main/poster/pelawangan.jpg",
            "Pelawangan Sembalun",
            "East Lombok",
            "Hiking, lake, mountain and camping",
        )
    }
}