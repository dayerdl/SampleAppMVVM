# Future workshops - Code test

Definition: Rest Client to display Articles from API of Future Workshop

Architecture:
- Use of MVVM

- Structure of features by packages. Each feature contains follows separation of concerns:
  - UI: Contain the views
  - ViewModels
  - Repository
  - DI: Dependencies for Dagger Modules

- DataBase: Store the favourite articles using Room.

Security:
Use of JetPack EncryptedSharedPreferences to encrypt and store the token generated by the API.
