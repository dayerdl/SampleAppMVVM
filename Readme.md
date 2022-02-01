Definition: Rest Client to display Articles from a Rest API

Architecture:
- Use of MVVM

- Structure of features by packages. Each feature contains follows separation of concerns:
  - UI: Contain the views -> Use of Compose to represent the views
  - ViewModels -> Use of Android viewmodel and Livedata to bind the views.
  - Repository
  - DI: Dependencies for Dagger Modules

- DataBase: Store the favourite articles using Room.

Security:
Use of JetPack EncryptedSharedPreferences to encrypt and store the token generated by the API.
