package com.example.wheaterapiconsummer.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.example.wheaterapiconsummer.viewmodel.WeatherViewModel

@Composable
fun MainScreen(viewModel: WeatherViewModel = hiltViewModel()) {

    // Collect state from the ViewModel
    val state by viewModel.state.collectAsState()

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.onEvent(OnClickFetch(""))
    }

    WeatherView(
        state = state,
        onClickCountUp = { query: String -> viewModel.onEvent(OnClickFetch(query)) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WeatherView(
    state: ScreenState,
    onClickCountUp: (query: String) -> Unit
) {
    var active by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("")}
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it  },
            onSearch = {
                active = false
                onClickCountUp.invoke(it)
            },
            active = active,
            onActiveChange = {},
            modifier = Modifier
                .padding(start = 12.dp, top = 2.dp, end = 12.dp, bottom = 12.dp),

            placeholder = { Text("Search") },

            trailingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            },
            colors = SearchBarDefaults.colors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            tonalElevation = 0.dp,
        ){}

        when (state.viewState) {
            ViewState.NO_RESULT, ViewState.ERROR -> NoResult()
            ViewState.NO_SELECTION -> NoSelection()
            ViewState.SHOW_RESULT -> SearchResult(model = state.weatherModel!!)
        }
    }
}
