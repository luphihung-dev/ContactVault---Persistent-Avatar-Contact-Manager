# ContactVault — Persistent Avatar Contact Manager

A native Android contact manager built with Kotlin, Room Database, RecyclerView, and predefined resource-based avatars.

![Android](https://img.shields.io/badge/Android-Native-3DDC84?style=flat-square&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-2.2.10-7F52FF?style=flat-square&logo=kotlin&logoColor=white)
![Room Database](https://img.shields.io/badge/Room-Database-2563EB?style=flat-square)
![RecyclerView](https://img.shields.io/badge/RecyclerView-List%20UI-FCA311?style=flat-square)
![Material Components](https://img.shields.io/badge/Material-Components-14213D?style=flat-square)
![Architecture](https://img.shields.io/badge/Architecture-MVVM--style-6B7280?style=flat-square)

## Preview

| Main Screen | Add Contact | Avatar Picker |
| --- | --- | --- |
| ![Main Screen](docs/screenshots/main-screen.png) | ![Add Contact](docs/screenshots/add-contact.png) | ![Avatar Picker](docs/screenshots/avatar-picker.png) |

| Validation | Persistence |
| --- | --- |
| ![Validation](docs/screenshots/validation.png) | ![Persistence](docs/screenshots/persistence.png) |

## Project Overview

ContactVault is a native Android contact manager that stores contact information locally and persistently. Users can create, update, and delete contacts, assign each contact a predefined avatar, and view all saved contacts in a clean RecyclerView-based interface.

The app is built with Kotlin and follows a small MVVM-style structure using Room Database, Repository, ViewModel, Kotlin Coroutines, and Flow.

## COMP1786 Exercise 3 Context

This project was developed for `COMP1786 Mobile Application Design and Development`, Exercise 3: Android Persistence. It extends the Android Persistence / Lecture 5 `ContactDatabase` concept into a more complete contact management app with persistent storage, structured resources, input validation, and a polished Material UI.

## Core Features

### Contact Management

- Add new contacts.
- Edit existing contacts.
- Delete contacts with confirmation.
- Display all saved contacts in a RecyclerView.
- Show each contact's avatar, name, phone number, and note/email.

### Persistence

- Store contact data locally using Room Database.
- Persist saved contacts after closing and reopening the app.
- Observe database changes using Kotlin Flow for automatic UI updates.

### Avatar Selection

- Choose avatars from predefined drawable resources.
- Store avatar resource names in Room instead of storing image files.
- Use `AvatarCatalog` to map stored avatar names to drawable resource IDs.

### UI/UX

- Material-styled interface with clear spacing and readable typography.
- FloatingActionButton for adding contacts.
- MaterialCardView contact rows.
- MaterialButton actions.
- Empty state for first-time use.
- Organized Android resources for text, colors, dimensions, themes, drawables, and layouts.

### Validation

- Contact name is required.
- Phone number is required.
- Validation errors are shown through `TextInputLayout` error messages.

## Tech Stack

- Kotlin
- Android XML Views
- Room Database
- Kotlin Coroutines and Flow
- AndroidX Lifecycle ViewModel
- RecyclerView and ListAdapter
- ViewBinding
- Material Components for Android
- AppCompat
- Gradle Kotlin DSL

## Architecture Overview

The project uses a lightweight MVVM-style architecture with clear separation between data, business logic, and UI layers.

| Component | Responsibility |
| --- | --- |
| `Contact.kt` | Room entity representing a saved contact. |
| `ContactDao.kt` | DAO for observing contacts and performing CRUD operations. |
| `ContactDatabase.kt` | Room database singleton. |
| `ContactRepository.kt` | Separates persistence operations from the UI layer. |
| `ContactViewModel.kt` | Exposes contact data and performs save/delete operations in `viewModelScope`. |
| `MainActivity.kt` | Displays the contact list, empty state, add action, edit action, and delete confirmation. |
| `AddEditContactActivity.kt` | Handles add/edit form logic, validation, saving, and avatar selection. |
| `ContactAdapter.kt` | Binds contact data to RecyclerView card items. |
| `AvatarCatalog.kt` | Maintains available avatar options and maps avatar names to drawable resources. |
| `AvatarAdapter.kt` | Displays avatar options in a grid picker. |

## How Persistence Works

Room creates a local SQLite database named `contact_vault_database`. Contact records are stored in the `contacts` table with the following fields:

- `id`
- `name`
- `phoneNumber`
- `note`
- `avatarName`

`ContactDao` exposes a `Flow<List<Contact>>`, which is collected by `MainActivity`. When a contact is inserted, updated, or deleted, Room emits the updated list and the RecyclerView refreshes automatically.

## How Avatar Selection Works

Avatar images are maintained as drawable resources under `app/src/main/res/drawable`. The app does not store image files, file paths, or URIs in the database. Instead, it stores a resource name such as `avatar_blue`.

`AvatarCatalog` maps each stored avatar name to the correct drawable resource ID, for example:

```kotlin
AvatarOption("avatar_blue", R.drawable.avatar_blue, "Blue")
```

This keeps avatar handling simple, reliable, and aligned with Android resource management.

## Project Structure

```text
app/src/main/java/com/example/projectexercise3/
|-- MainActivity.kt
|-- AddEditContactActivity.kt
|-- data/
|   |-- Contact.kt
|   |-- ContactDao.kt
|   |-- ContactDatabase.kt
|   `-- ContactRepository.kt
`-- ui/
    |-- AvatarAdapter.kt
    |-- AvatarCatalog.kt
    |-- ContactAdapter.kt
    `-- ContactViewModel.kt

app/src/main/res/
|-- drawable/        # avatars, icons, and visual backgrounds
|-- layout/          # activity, dialog, and RecyclerView item layouts
|-- values/          # strings, colors, dimensions, and theme resources
`-- xml/             # backup and data extraction rules
```

## How to Run the Project

### Android Studio

1. Open the project in Android Studio.
2. Allow Gradle to sync dependencies.
3. Select an Android emulator or physical device.
4. Run the `app` configuration.
5. Add contacts, close the app, and reopen it to verify persistence.

### Command Line

macOS/Linux:

```bash
./gradlew assembleDebug
```

Windows PowerShell:

```powershell
.\gradlew.bat assembleDebug
```

## Testing / Build Verification

The project was checked using the following command:

```powershell
.\gradlew.bat assembleDebug
```

Expected result:

```text
BUILD SUCCESSFUL
```

## Technical Highlights

- Room Database provides persistent local SQLite storage.
- Kotlin Flow keeps the RecyclerView automatically synchronized with database changes.
- Repository and ViewModel separate database operations from Activity code.
- RecyclerView `ListAdapter` and `DiffUtil` provide efficient list updates.
- Material Components and XML resources create a consistent, polished interface.
- Avatar selection stores resource names instead of image files, keeping persistence lightweight.
- Required field validation is handled with `TextInputLayout` error states.

## Future Improvements

- Add contact search.
- Add favourite contacts.
- Add contact groups or categories.
- Add import/export support.
- Refine dark mode styling.

## Author

- Student name: Lữ Phi Hùng
- Student ID: GCS220588
- Module: COMP1786 Mobile Application Design and Development
- Exercise: Exercise 3 - Android Persistence

## License / Educational Purpose

This project was developed for educational purposes as part of `COMP1786 Mobile Application Design and Development`.