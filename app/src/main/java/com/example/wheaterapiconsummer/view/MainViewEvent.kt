package com.example.wheaterapiconsummer.view

sealed class MainViewEvent
data class OnClickFetch(val query: String) : MainViewEvent()
