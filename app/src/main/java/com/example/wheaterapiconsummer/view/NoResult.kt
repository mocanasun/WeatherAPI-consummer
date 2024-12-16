package com.example.wheaterapiconsummer.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.wheaterapiconsummer.ui.theme.WheaterApiConsummerTheme

@Composable
fun NoResult() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
    }
}

@Preview(showBackground = true)
@Composable
fun NoResultPreview() {
    WheaterApiConsummerTheme {
        NoResult()
    }
}