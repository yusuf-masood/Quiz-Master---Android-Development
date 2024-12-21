# Quiz-Master---Android-Development

# Android-Development-Lab3
# QuizMaster App

QuizMaster App is an Android application that allows users to engage with quizzes on various topics, create custom quizzes, and manage user authentication locally. It leverages a local Room database and integrates external APIs to deliver a modern, feature-rich user experience.

## Features

1. **User Authentication**
   - Secure login and signup functionality using a Room database.
   - Ensures access is restricted to registered users.

2. **API-Driven Quizzes**
   - Fetches quiz questions from external APIs, such as The Trivia API and Open Trivia Database.
   - Presents quizzes in an efficient and visually appealing RecyclerView interface.

3. **Custom Quiz Management**
   - Enables users to create, save, and view their custom quizzes.
   - Stores custom quizzes locally in a Room database.

4. **User Interface**
   - Utilizes Material Design for a polished and user-friendly experience.
   - Fragment-based navigation ensures seamless transitions between app sections.

5. **Architecture & Best Practices**
   - Implements MVVM (Model-View-ViewModel) architecture for modular and scalable development.
   - Utilizes Kotlin Coroutines for asynchronous operations and better performance.
   - Adheres to Android development best practices for clean and maintainable code.

## Technical Highlights

- **Room Database**:
  - Manages user credentials and stores custom quizzes locally.

- **Retrofit**:
  - Handles API calls to fetch quizzes from external sources.

- **RecyclerView with DiffUtil**:
  - Efficiently displays and updates quiz lists.

- **Fragment Navigation**:
  - Provides structured navigation for improved user experience.

- **Material Design**:
  - Enhances the visual appeal and usability of the app.

- **MVVM Architecture**:
  - Utilizes LiveData and ViewModel for reactive and modular application logic.

## Installation

### Prerequisites:
- Android Studio installed on your machine.
- Minimum SDK version: 21 (Android 5.0 Lollipop).

### Steps:
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/quizmaster-app.git
   
## Contribution:
- Abdullaev Shakhzod - 22B031601
- Sayed Yusuf Masood - 21B031339
