# TahalufApp

This is a modular mobile application developed in Kotlin and XML utilizing Jetpack components. The application follows the MVVM clean architecture pattern and consists of two modules: a Listing screen and a Details screen. The application fetches data from an API, caches it in a local database, and supports offline functionality.

[Watch](https://github.com/user-attachments/assets/485da819-64cf-4192-8f7b-13482a3b4bb3)

## Modules

### Module A: Listing Screen

- Displays a list of universities.
- Fetches data from the API and caches it in the local Room database.
- If the API call fails, it retrieves data from the database.
- Displays an error message if no data is available in the database.
- Clicking on an item navigates to the Details screen.
- Provides a refresh button to reload data from the API.

### Module B: Details Screen

- Displays details of the selected university.
- Data is passed from the Listing screen; no additional API call is made.
- Includes a button to dismiss the screen and return to the Listing screen, triggering a refresh.

## Architecture

The application follows the MVVM clean architecture pattern:

- **Model:** Handles data operations, including network requests and database interactions.
- **ViewModel:** Manages UI-related data and business logic.
- **View:** Consists of the XML layouts and activities/fragments that display data.

## Dependencies

- **Retrofit:** For network requests.
- **Room:** For local database storage.
- **Dagger 2:** For dependency injection.
- **Jetpack Components:** ViewModel, LiveData, and other architecture components.




## License

* This project is licensed under the MIT License - see the LICENSE file for details.
