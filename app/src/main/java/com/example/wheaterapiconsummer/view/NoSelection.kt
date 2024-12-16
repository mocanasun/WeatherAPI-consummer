package com.example.wheaterapiconsummer.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.wheaterapiconsummer.R
import com.example.wheaterapiconsummer.ui.theme.WheaterApiConsummerTheme

@Composable
fun NoSelection(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.no_city_selected), fontSize = 40.sp, fontWeight = FontWeight.Bold)

        Text(text = stringResource(R.string.please_search_for_a_city), fontSize = 18.sp)
    }
}
