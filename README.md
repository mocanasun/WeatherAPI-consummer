Libs: 
 - Image load - coil3, 
 - Json serialization - kotlinx-serialization-json, 
 - DI - hilt,
 - Api calls - retrofit
 - UI -  JetPack Compose,
 - Local Storage - SharedPreferences
 - Background operations - coroutine
 - Reactive communication - StateFlow



Architecture pattern - MVVM

The app components interacts via abstract layers - each component is completely independent and doesn’t depend on a specific implementation.
 - the non UI objects receive interfaces’ implementation  objects 
 - the UI objects are receiving updates via StateFlow events.
 - the viewModel handles user events, computes new state and update StateFlow value.
 - Error handling for retrofit calls


The app :

 - At first run: you see the message “No city selected”
 - After a search with error or no result an empty screen will be shown.
 - After a valid search a card will be shown that could be expanded with a click and reverse is possible.
 - The last selection is saved that will be visible at next app run.
