package com.example.wheaterapiconsummer.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.size.Size
import com.example.wheaterapiconsummer.R
import com.example.wheaterapiconsummer.model.WeatherModel

@Composable
fun SearchResult(
    model: WeatherModel
) {
    var expandedState by remember { mutableStateOf(false) }
    Column {

        if (expandedState) {
            WeatherExpanded(model = model,
                            onClick = {
                                expandedState = !expandedState
                            })
        } else {
            WeatherCollapsed(model = model,
                             onClick = {
                                 expandedState = !expandedState
                             })
        }

    }
}

@Composable
fun CategoryImageFromURLWithPlaceHolder(imageUrl: String){

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .size(Size.ORIGINAL)
            .build(),
        placeholder = painterResource(R.drawable.placeholder),
        contentDescription = stringResource(R.string.app_name),
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .height(100.dp)
            .width(100.dp)
    )
}

@Composable
fun WeatherDetails(
    model: WeatherModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly)
        {
            // Humidity
            GridEntry(title = "Humidity", value = model.humidity)
            // UV
            GridEntry(title = "UV", value = model.uvIndex)
            // Feels Like
            GridEntry(title = "Feels Like", value = model.feelsLikeC)
        }
    }
}

@Composable
fun GridEntry(title: String,
        value: Double?,
        titleFontSize: TextUnit = 14.sp,
        valueFontSize: TextUnit = 24.sp,
        titleFontWeight: FontWeight = FontWeight.Light,
        valueFontWeight: FontWeight = FontWeight.Bold,) {
        // Feels like
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title,
                 fontSize = titleFontSize,
                 fontWeight = titleFontWeight)
            Text(text = "$value",
                 fontSize = valueFontSize,
                 fontWeight = valueFontWeight)
        }
}

@Composable
fun WeatherExpanded( model: WeatherModel,
    onClick: () -> Unit,
    titleFontSize: TextUnit = MaterialTheme.typography.titleLarge.fontSize,
    valueFontSize: TextUnit = 48.sp,
    titleFontWeight: FontWeight = FontWeight.Bold,
    valueFontWeight: FontWeight = FontWeight.Bold,) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.spacedBy(30.dp),
           modifier = Modifier
               .fillMaxWidth()
               .clickable { onClick.invoke() }) {
        CategoryImageFromURLWithPlaceHolder("https:${model.condition?.icon}")
        Text(text = "${model.location}",
             fontSize = titleFontSize,
             fontWeight = titleFontWeight)
        Text(text = "${model.tempCelsius}",
             fontSize = valueFontSize,
             fontWeight = valueFontWeight)
        WeatherDetails(model = model)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherCollapsed(model: WeatherModel,
    onClick: () -> Unit,
    titleFontSize: TextUnit = MaterialTheme.typography.titleLarge.fontSize,
    titleFontWeight: FontWeight = FontWeight.Bold,
    padding: Dp = 12.dp) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier
                        .padding(padding)
                ) {
                    Text(text = model.location!!,
                         fontSize = titleFontSize,
                         fontWeight = titleFontWeight)
                    Text(text = "${model.tempCelsius}",
                         fontSize = 48.sp,
                         fontWeight = FontWeight.Bold)
                }

                CategoryImageFromURLWithPlaceHolder("https:${model.condition?.icon}")
            }
        }
    }
}
