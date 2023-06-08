# MARVEL WORLD

<p align="center">    
    <img alt="Marvel App Logo" src="app/src/main/ic_launcher-playstore.png" width=250px/>    
</p>

This repository contains the technical test corresponding to Mango España. It is a list-detail style application that puts to the test everything learned about Kotlin. The application retrieves data from the Marvel API ([https://developer.marvel.com/](https://developer.marvel.com/)).

The technologies used have been:

-   Kotlin
-   MVVM design pattern
-   Dependency injection with Dagger-Hilt
-   Flows
-   Retrofit
-   Design with Material 3 components
-   Pagination with the Paging 3 library
-   Cache with ROOM
-   Version control with Git Flow using the Git extension (git flow commands)

# Design

-   Material 3 components have been used
-   MVVM design pattern
-   User interface designed in Kotlin and Jetpack Compose, along with the detail view in XML
-   Architecture with three layers: Data, Domain, and UI

# Features

-   Splash Screen with an AVD, following Google's recommended practice
-   Non-blocking API calls
-   Search for cached characters through a dedicated view, with a search history
-   Characters are loaded in batches of 20 as you scroll down

# How to compile the application:

You need to have a file named local.properties in the same route as the /app folder, and add the following properties (below the sdk location):

publicKey=f283ff44e78510b2313f532b76ff8eb6

privateKey=56d84460b05555369182257365dc23429e51dd34

# Proposals for improvement and known issues

-   The design could be improved to be more user-friendly
-   Transactions between different components
-   Sometimes, if there is no internet connection while in the detail view, the application crashes when going back
