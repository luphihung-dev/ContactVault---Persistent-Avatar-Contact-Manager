# ContactVault — Persistent Avatar Contact Manager

ContactVault is a native Android contact manager built for COMP1786 Exercise 3. It stores contacts persistently with Room Database and lets users assign a predefined avatar drawable to each contact.

## COMP1786 Exercise 3 Context

This project extends the Android Persistence / Lecture 5 ContactDatabase concept into a more complete Android app. The app demonstrates persistent storage, structured Android resources, RecyclerView presentation, input validation, and clean Kotlin architecture.

## Features

- Add, edit, and delete contacts.
- Store contact name, phone number, email address or short note, and avatar resource name.
- Persist data after the app is closed and reopened using Room.
- Display contacts in a RecyclerView with avatar, name, phone number, and note.
- Choose from multiple predefined drawable avatar resources.
- Show an empty state when no contacts exist.
- Validate required fields with user-friendly errors.
- Use centralized Android resources for strings, colors, dimensions, themes, icons, and avatars.
- Professional Material-styled UI suitable for coursework screenshots and logbook explanation.

## Technologies Used

- Kotlin
- Android Views and XML layouts
- RecyclerView
- Room Database
- Kotlin Coroutines and Flow
- AndroidX Lifecycle ViewModel
- ViewBinding
- Material Components for Android

## App Architecture

The project follows a small MVVM-style architecture:

- `Contact`: Room entity representing one saved contact.
- `ContactDao`: database queries and write operations.
- `ContactDatabase`: Room database singleton.
- `ContactRepository`: persistence abstraction used by the ViewModel.
- `ContactViewModel`: exposes contacts as Flow and performs save/delete operations.
- `MainActivity`: displays the contact list and empty state.
- `AddEditContactActivity`: handles contact creation, editing, validation, and avatar selection.
- `ContactAdapter`: RecyclerView adapter for contact cards.
- `AvatarAdapter`: RecyclerView adapter for avatar grid selection.

## How Persistence Works

Room stores all contacts in a local SQLite database named `contact_vault_database`. The `contacts` table contains the contact id, name, phone number, note, and selected avatar resource name. The UI observes Room `Flow` streams, so changes are reflected automatically when contacts are inserted, updated, or deleted.

## How Avatar Selection Works

Avatars are predefined Android drawable resources stored in `app/src/main/res/drawable`. The app stores the selected avatar's resource name, such as `avatar_blue`, in the Room database. When rendering the contact list or edit screen, `AvatarCatalog` maps that stored name back to the correct drawable resource identifier.

## Project Structure

```text
app/src/main/java/com/example/projectexercise3/
├── MainActivity.kt
├── AddEditContactActivity.kt
├── data/
│   ├── Contact.kt
│   ├── ContactDao.kt
│   ├── ContactDatabase.kt
│   └── ContactRepository.kt
└── ui/
    ├── AvatarAdapter.kt
    ├── AvatarCatalog.kt
    ├── ContactAdapter.kt
    └── ContactViewModel.kt

app/src/main/res/
├── drawable/        # icons, backgrounds, and avatar resources
├── layout/          # activity, dialog, and RecyclerView item layouts
└── values/          # strings, colors, dimens, and theme resources
```

## How to Run the Project

1. Open the project in Android Studio.
2. Let Gradle sync dependencies.
3. Select an emulator or physical Android device.
4. Run the `app` configuration.
5. Add contacts and restart the app to verify persistence.

You can also build from the command line:

```bash
./gradlew assembleDebug
```

On Windows PowerShell:

```powershell
.\gradlew.bat assembleDebug
```

## Screenshots

Add screenshots for the final logbook submission:

- Main contact list screen: `[screenshot placeholder]`
- Empty state screen: `[screenshot placeholder]`
- Add contact screen: `[screenshot placeholder]`
- Avatar picker dialog: `[screenshot placeholder]`
- Edit contact screen: `[screenshot placeholder]`

## Author

- Student name: `[leave placeholder]`
- Student ID: `GCS220588`

## Notes for Logbook Submission

- Explain how Room persists contact data in SQLite.
- Include screenshots showing add, edit, delete, avatar selection, and app restart persistence.
- Mention that avatar choices are maintained as Android drawable resources and stored by resource name.
- Highlight input validation for required name and phone fields.
- Reference the MVVM-style separation between data, repository, ViewModel, and UI layers.

## GitHub Repository

https://github.com/luphihung-dev/Project-exercise-3.git
