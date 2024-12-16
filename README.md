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

